package com.nilo.wms.service.impl;

import com.alibaba.fastjson.JSON;
import com.nilo.mq.model.NotifyRequest;
import com.nilo.mq.producer.AbstractMQProducer;
import com.nilo.wms.common.enums.InboundStatusEnum;
import com.nilo.wms.common.enums.OutBoundStatusEnum;
import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.XmlUtil;
import com.nilo.wms.dao.flux.StorageDao;
import com.nilo.wms.dao.platform.InboundDao;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.HttpRequest;
import com.nilo.wms.service.InboundService;
import com.nilo.wms.service.RedisUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by admin on 2018/3/19.
 */
@Service
public class InboundServiceImpl implements InboundService {
    private static final Logger logger = LoggerFactory.getLogger(InboundServiceImpl.class);

    @Resource(name = "fluxHttpRequest")
    private HttpRequest<FLuxRequest, FluxResponse> fluxHttpRequest;
    @Autowired
    @Qualifier("notifyDataBusProducer")
    private AbstractMQProducer notifyDataBusProducer;
    @Autowired
    private InboundDao inboundDao;
    @Autowired
    private StorageDao storageDao;
    @Autowired
    private BasicDataService basicDataService;

    @Override
    @Transactional
    public void createInBound(InboundHeader inbound) {

        AssertUtil.isNotNull(inbound, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(inbound.getAsnNo(), CheckErrorCode.CLIENT_ORDER_EMPTY);
        AssertUtil.isNotBlank(inbound.getAsnType(), CheckErrorCode.ORDER_TYPE_EMPTY);
        AssertUtil.isNotBlank(inbound.getOrderTime(), CheckErrorCode.ADD_TIME_EMPTY);
        AssertUtil.isNotBlank(inbound.getWarehouseId(), CheckErrorCode.WAREHOUSE_EMPTY);
        AssertUtil.isNotBlank(inbound.getCustomerId(), CheckErrorCode.CLIENT_ID_EMPTY);
        AssertUtil.isNotNull(inbound.getItemList(), CheckErrorCode.ITEM_EMPTY);

        //保存
        InboundDO inboundDO = inboundDao.queryByAsnNo(inbound.getAsnNo());
        if (inboundDO != null) return;
        InboundDO insert = new InboundDO();
        insert.setAsnNo2(inbound.getAsnNo2());
        insert.setAsnNo(inbound.getAsnNo());
        insert.setCustomerId(inbound.getCustomerId());
        insert.setWarehouseId(inbound.getWarehouseId());
        insert.setStatus(InboundStatusEnum.create.getCode());
        insert.setAsnType(inbound.getAsnType());
        inboundDao.insert(insert);


        //构建flux请求对象
        FLuxRequest request = new FLuxRequest();

        String xmlData = XmlUtil.BeanToXML(inbound);
        //添加xml数据前缀
        xmlData = "<xmldata>" + xmlData + "</xmldata>";
        request.setData(xmlData);

        switch (inbound.getAsnType()) {
            case "PO": {
                request.setMessageid("ASN");
                request.setMethod("putASNData");
                break;
            }
            case "TH":
            case "TR":
            case "WX":
            case "JS":
            case "PY": {
                request.setMessageid("TRASN");
                request.setMethod("putTRASNData");
                break;
            }
            default:
                throw new WMSException(BizErrorCode.ORDER_TYPE_NOT_EXIST);
        }

        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }
    }

    @Override
    public void cancelInBound(String asnNo) {

        AssertUtil.isNotBlank(asnNo, CheckErrorCode.CLIENT_ORDER_EMPTY);

        InboundDO inboundDO = inboundDao.queryByAsnNo(asnNo);
        if (inboundDO == null) throw new WMSException(BizErrorCode.NOT_EXIST, asnNo);
        if (inboundDO.getStatus() == InboundStatusEnum.cancelled.getCode()) return;

        // 通知flux
        FLuxRequest request = new FLuxRequest();
        String xmlData = "<xmldata><data><ordernos><OrderNo>" + asnNo + "</OrderNo><OrderType>" + inboundDO.getAsnType() + "</OrderType><CustomerID>" + inboundDO.getCustomerId() + "</CustomerID><WarehouseID>" + inboundDO.getWarehouseId() + "</WarehouseID></ordernos></data></xmldata>";
        request.setData(xmlData);
        request.setMessageid("ASNC");
        request.setMethod("cancelASNData");

        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }
        //修改状态
        InboundDO update = new InboundDO();
        update.setAsnNo(asnNo);
        update.setStatus(InboundStatusEnum.cancelled.getCode());
        inboundDao.update(update);
    }

    @Override
    public void confirmASN(List<InboundHeader> list) {

        if (list == null || list.size() == 0) {
            return;
        }
        String customerId = "KILIMALL";
        String warehouseId = "KE01";
        //查询是否已经通知过
        Iterator<InboundHeader> iterator = list.iterator();
        while (iterator.hasNext()) {
            InboundHeader in = iterator.next();
            InboundDO inboundDO = inboundDao.queryByAsnNo(in.getAsnNo());
            if (inboundDO == null) {
                iterator.remove();
            } else if (inboundDO.getStatus() == InboundStatusEnum.closed.getCode()) {
                iterator.remove();
            }
        }

        MerchantConfig merchantConfig = JSON.parseObject(RedisUtil.get("System_merchant_config" + "1"),
                MerchantConfig.class);
        InterfaceConfig interfaceConfig = JSON.parseObject(
                RedisUtil.hget("System_interface_config" + "1", "wms_inbound_notify"), InterfaceConfig.class);

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("status", 99);
        String data = JSON.toJSONString(map);
        Map<String, String> params = new HashMap<>();
        params.put("method", interfaceConfig.getOp());
        params.put("sign", createNOSSign(data, merchantConfig.getKey()));
        params.put("data", data);
        params.put("app_key", "wms");
        params.put("request_id", UUID.randomUUID().toString());
        params.put("timestamp", "" + DateUtil.getSysTimeStamp());

        NotifyRequest notify = new NotifyRequest();
        notify.setParam(params);
        notify.setUrl(interfaceConfig.getUrl());
        try {
            notifyDataBusProducer.sendMessage(notify);
        } catch (Exception e) {
            logger.error("confirmSO send message failed.", e);
        }

        //更新inbound状态
        List<String> skuList = new ArrayList<>();
        for (InboundHeader in : list) {
            InboundDO update = new InboundDO();
            update.setAsnNo(in.getAsnNo());
            update.setStatus(InboundStatusEnum.closed.getCode());
            inboundDao.update(update);
            for (InboundItem item : in.getItemList()) {
                skuList.add(item.getSku());
            }
        }


        // 查询sku 仓库实际库存
        StorageParam param = new StorageParam();
        param.setSku(skuList);
        List<StorageInfo> storageList = storageDao.queryBy(param);

        //获取redis锁
        Jedis jedis = RedisUtil.getResource();
        String requestId = UUID.randomUUID().toString();
        boolean getLock = RedisUtil.tryGetDistributedLock(jedis, RedisUtil.LOCK_KEY, requestId);
        if (!getLock) throw new WMSException(SysErrorCode.SYSTEM_ERROR);
        //更新库存
        for (StorageInfo i : storageList) {
            String key = RedisUtil.getSkuKey(customerId, warehouseId, i.getSku());
            String lockSto = RedisUtil.hget(key, RedisUtil.LOCK_STORAGE);
            i.setLockStorage(lockSto == null ? 0 : Integer.parseInt(lockSto));
            jedis.hset(key, RedisUtil.STORAGE, "" + i.getStorage());
        }
        RedisUtil.releaseDistributedLock(jedis, RedisUtil.LOCK_KEY, requestId);

        basicDataService.storageChangeNotify(customerId, warehouseId, storageList);
    }

    @Override
    public FluxInbound queryFlux(String asnNo) {

        return null;
    }

    private String createNOSSign(String data, String key) {
        String str = key + data + key;
        return DigestUtils.md5Hex(str).toUpperCase();
    }

}
