package com.nilo.wms.service.impl;

import com.alibaba.fastjson.JSON;
import com.nilo.mq.model.NotifyRequest;
import com.nilo.mq.producer.AbstractMQProducer;
import com.nilo.wms.common.enums.OutBoundStatusEnum;
import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.common.util.XmlUtil;
import com.nilo.wms.dao.flux.FluxOutboundDao;
import com.nilo.wms.dao.platform.OutboundDao;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.HttpRequest;
import com.nilo.wms.service.OutboundService;
import com.nilo.wms.service.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by admin on 2018/3/19.
 */
@Service
public class OutboundServiceImpl implements OutboundService {
    private static final Logger logger = LoggerFactory.getLogger(OutboundServiceImpl.class);

    @Resource(name = "fluxHttpRequest")
    private HttpRequest<FLuxRequest, FluxResponse> fluxHttpRequest;
    @Autowired
    @Qualifier("notifyDataBusProducer")
    private AbstractMQProducer notifyDataBusProducer;
    @Autowired
    private OutboundDao outboundDao;
    @Autowired
    private FluxOutboundDao fluxOutboundDao;
    @Autowired
    private BasicDataService basicDataService;

    @Override
    @Transactional
    public void createOutBound(OutboundHeader outBound) {

        AssertUtil.isNotNull(outBound, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(outBound.getWarehouseId(), CheckErrorCode.WAREHOUSE_EMPTY);
        AssertUtil.isNotBlank(outBound.getCustomerId(), CheckErrorCode.CLIENT_ID_EMPTY);

        AssertUtil.isNotBlank(outBound.getOrderNo(), CheckErrorCode.CLIENT_ORDER_EMPTY);
        AssertUtil.isNotBlank(outBound.getOrderType(), CheckErrorCode.ORDER_TYPE_EMPTY);
        AssertUtil.isNotBlank(outBound.getChannel(), CheckErrorCode.DELIVERY_TYPE_EMPTY);
        AssertUtil.isNotBlank(outBound.getIsCod(), CheckErrorCode.IS_POD_EMPTY);
        AssertUtil.isNotBlank(outBound.getDeliveryNo(), CheckErrorCode.WAYBILL_EMPTY);
        AssertUtil.isFalse(outBound.getOrderAmount() == 0, CheckErrorCode.ORDER_AMOUNT_EMPTY);
        AssertUtil.isNotBlank(outBound.getOrderTime(), CheckErrorCode.ADD_TIME_EMPTY);
        AssertUtil.isNotNull(outBound.getItemList(), CheckErrorCode.ITEM_EMPTY);
        AssertUtil.isNotNull(outBound.getReceiverInfo(), CheckErrorCode.RECEIVER_INFO_EMPTY);
        AssertUtil.isNotBlank(outBound.getReceiverInfo().getReceiverAddress(), CheckErrorCode.RECEIVER_ADDRESS_EMPTY);
        AssertUtil.isNotBlank(outBound.getReceiverInfo().getReceiverName(), CheckErrorCode.RECEIVER_NAME_EMPTY);
        AssertUtil.isNotBlank(outBound.getReceiverInfo().getReceiverPhone(), CheckErrorCode.RECEIVER_PHONE_EMPTY);

        //保存
        OutboundDO outboundDO = outboundDao.queryByReferenceNo(outBound.getOrderNo());
        if (outboundDO != null) return;
        OutboundDO insert = new OutboundDO();
        insert.setReferenceNo(outBound.getOrderNo());
        insert.setOrderType(outBound.getOrderType());
        insert.setCustomerId(outBound.getCustomerId());
        insert.setWarehouseId(outBound.getWarehouseId());
        insert.setStatus(OutBoundStatusEnum.create.getCode());
        outboundDao.insert(insert);


        //构建flux请求对象
        FLuxRequest request = new FLuxRequest();
        outBound.setReceiverAddress(outBound.getReceiverInfo().getReceiverAddress());
        outBound.setReceiverCity(outBound.getReceiverInfo().getReceiverCity());
        outBound.setReceiverName(outBound.getReceiverInfo().getReceiverName());
        outBound.setReceiverPhone(outBound.getReceiverInfo().getReceiverPhone());

        String xmlData = XmlUtil.BeanToXML(outBound);
        //添加xml数据前缀
        xmlData = "<xmldata>" + xmlData + "</xmldata>";
        request.setData(xmlData);

        switch (outBound.getOrderType()) {
            case "SO":
            case "SELL": {
                request.setMessageid("SO");
                request.setMethod("putSOData");
                break;
            }
            case "WX":
            case "CT":
            case "PK":
            case "HH":
            case "BF": {
                request.setMessageid("CGSO");
                request.setMethod("putCGSOData");
                break;
            }
            default:
                throw new WMSException(BizErrorCode.ORDER_TYPE_NOT_EXIST);
        }

        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }

        //FBK扣减库存
        if (StringUtil.equalsIgnoreCase("SELL", outBound.getOrderType())) {
            basicDataService.successStorage(outBound.getOrderNo(), outBound.getCustomerId(), outBound.getWarehouseId());
        }
    }

    @Override
    @Transactional
    public void cancelOutBound(String orderNo) {

        AssertUtil.isNotBlank(orderNo, CheckErrorCode.CLIENT_ORDER_EMPTY);

        OutboundDO outboundDO = outboundDao.queryByReferenceNo(orderNo);
        if (outboundDO == null) throw new WMSException(BizErrorCode.NOT_EXIST, orderNo);
        if (outboundDO.getStatus() == OutBoundStatusEnum.cancelled.getCode()) return;

        OutboundDO update = new OutboundDO();
        update.setReferenceNo(orderNo);
        update.setStatus(OutBoundStatusEnum.cancelled.getCode());
        outboundDao.update(update);

        OutBoundSimpleBean cancelOrder = new OutBoundSimpleBean();
        cancelOrder.setOrderNo(orderNo);
        cancelOrder.setCustomerID(outboundDO.getCustomerId());
        cancelOrder.setWarehouseID(outboundDO.getWarehouseId());
        cancelOrder.setOrderType(outboundDO.getOrderType());

        //构建flux请求对象
        FLuxRequest request = new FLuxRequest();
        String xmlData = XmlUtil.BeanToXML(cancelOrder);
        //添加xml数据前缀
        xmlData = "<xmldata><data>" + xmlData + "</data></xmldata>";
        request.setData(xmlData);
        request.setMessageid("SOC");
        request.setMethod("cancelSOData");

        //调用flux 请求
        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }
    }

    @Override
    public void confirmSO(List<String> list, boolean result) {

        Jedis jedis = RedisUtil.getResource();
        MerchantConfig merchantConfig = JSON.parseObject(jedis.get("System_merchant_config" + "1"),
                MerchantConfig.class);
        InterfaceConfig interfaceConfig = JSON.parseObject(
                jedis.hget("System_interface_config" + "1", "wms_outbound_notify"), InterfaceConfig.class);
        RedisUtil.returnResource(jedis);

        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        if (result) {
            map.put("status", 99);
        } else {
            map.put("status", 90);
        }
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

    }

    @Override
    public FluxOutbound queryFlux(String orderNo) {

        FluxOutbound order = fluxOutboundDao.queryByReferenceNo(orderNo);
        if (order == null) return null;
        order.setStatusDesc(OutBoundStatusEnum.getEnum(order.getStatus()).getDesc_e());
        return order;
    }

    private String createNOSSign(String data, String key) {
        String str = key + data + key;
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    @XmlRootElement(name = "ordernos")
    private static class OutBoundSimpleBean {
        private String orderNo;

        private String orderType;

        private String customerId = "KILIMALL";

        private String warehouseId = "KE01";

        private String reason;

        @XmlElement(name = "OrderNo")
        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        @XmlElement(name = "OrderType")
        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getCustomerID() {
            return customerId;
        }

        @XmlElement(name = "CustomerID")
        public void setCustomerID(String customerID) {
            customerId = customerID;
        }

        public String getWarehouseID() {
            return warehouseId;
        }

        @XmlElement(name = "WarehouseID")
        public void setWarehouseID(String warehouseID) {
            warehouseId = warehouseID;
        }

        public String getReason() {
            return reason;
        }

        @XmlElement(name = "Reason")
        public void setReason(String reason) {
            this.reason = reason;
        }
    }

}
