package com.nilo.wms.service.impl;

import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.XmlUtil;
import com.nilo.wms.dao.flux.StorageDao;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.HttpRequest;
import com.nilo.wms.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by admin on 2018/3/20.
 */
@Service
public class BasicDataServiceImpl implements BasicDataService {

    @Resource(name = "fluxHttpRequest")
    private HttpRequest<FLuxRequest, FluxResponse> fluxHttpRequest;

    @Autowired
    private StorageDao storageDao;

    private static final String LOCK_KEY = "wms_redis_lock_key";

    @Override
    public void updateSku(List<SkuInfo> list) {

        AssertUtil.isNotNull(list, SysErrorCode.REQUEST_IS_NULL);

        for (SkuInfo s : list) {
            AssertUtil.isNotBlank(s.getSku(), CheckErrorCode.SKU_EMPTY);
            AssertUtil.isNotBlank(s.getDescE(), CheckErrorCode.SKU_DESC_EMPTY);
            AssertUtil.isNotBlank(s.getStoreId(), CheckErrorCode.STORE_EMPTY);
        }

        FLuxRequest request = new FLuxRequest();
        String xmlData = XmlUtil.ListToXML(list);
        //添加xml数据前缀
        xmlData = "<xmldata>" + xmlData + "</xmldata>";
        request.setData(xmlData);
        request.setMessageid("SKU");
        request.setMethod("putSKUData");
        //调用flux 请求
        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }
    }

    @Override
    public void updateSupplier(SupplierInfo supplierInfo) {

        AssertUtil.isNotNull(supplierInfo, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(supplierInfo.getCustomerId(), CheckErrorCode.STORE_EMPTY);
        AssertUtil.isNotBlank(supplierInfo.getDescE(), CheckErrorCode.STORE_DESC_EMPTY);
        AssertUtil.isNotBlank(supplierInfo.getAddress(), CheckErrorCode.STORE_ADDRESS_EMPTY);

        FLuxRequest request = new FLuxRequest();
        String xmlData = XmlUtil.BeanToXML(supplierInfo);
        //添加xml数据前缀
        xmlData = "<xmldata>" + xmlData + "</xmldata>";
        request.setData(xmlData);
        request.setMessageid("CUSTOMER");
        request.setMethod("putCustData");

        //调用flux 请求
        FluxResponse response = fluxHttpRequest.doRequest(request);
        if (!response.isSuccess()) {
            throw new RuntimeException(response.getReturnDesc());
        }
    }

    @Override
    public List<StorageInfo> queryStorage(StorageParam param) {

        AssertUtil.isNotNull(param, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(param.getCustomerId(), CheckErrorCode.CUSTOMER_EMPTY);
        AssertUtil.isNotBlank(param.getWarehouseId(), CheckErrorCode.WAREHOUSE_EMPTY);

        return storageDao.queryBy(param);
    }

    @Override
    public List<StorageInfo> queryStorageDetail(StorageParam param) {

        AssertUtil.isNotNull(param, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(param.getCustomerId(), CheckErrorCode.CUSTOMER_EMPTY);
        AssertUtil.isNotBlank(param.getWarehouseId(), CheckErrorCode.WAREHOUSE_EMPTY);
        AssertUtil.isNotNull(param.getSku(), CheckErrorCode.SKU_EMPTY);

        List<StorageInfo> list = storageDao.queryBy(param);
        String customerId = param.getCustomerId();
        String warehouseId = param.getWarehouseId();

        for (StorageInfo s : list) {
            String key = RedisUtil.getSkuKey(customerId, warehouseId, s.getSku());
            String lockSto = RedisUtil.hget(key, RedisUtil.LOCK_STORAGE);
            int lockStoInt = ((lockSto == null ? 0 : Integer.parseInt(lockSto)));
            s.setLockStorage(lockStoInt);
        }
        return list;
    }

    @Override
    public void lockStorage(OutboundHeader header) {

        //check
        AssertUtil.isNotNull(header, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(header.getOrderNo(), CheckErrorCode.CLIENT_ORDER_EMPTY);
        AssertUtil.isNotBlank(header.getCustomerId(), CheckErrorCode.CUSTOMER_EMPTY);
        AssertUtil.isNotBlank(header.getWarehouseId(), CheckErrorCode.WAREHOUSE_EMPTY);
        AssertUtil.isNotNull(header.getItemList(), CheckErrorCode.ITEM_EMPTY);
        for (OutboundItem item : header.getItemList()) {
            AssertUtil.isNotBlank(item.getSku(), CheckErrorCode.SKU_EMPTY);
            AssertUtil.isNotNull(item.getQty(), CheckErrorCode.QTY_EMPTY);
        }

        String customerId = header.getCustomerId().toLowerCase();
        String warehouseId = header.getWarehouseId().toLowerCase();

        // 判断订单号是否已锁定
        String orderNoKey = RedisUtil.getLockOrderKey(customerId, warehouseId, header.getOrderNo());
        boolean keyExist = RedisUtil.hasKey(orderNoKey);
        if (keyExist) {
            return;
        }

        //获取redis锁
        Jedis jedis = RedisUtil.getResource();
        String requestId = UUID.randomUUID().toString();
        boolean getLock = RedisUtil.tryGetDistributedLock(jedis, LOCK_KEY, requestId);
        if (!getLock) {
            throw new WMSException(SysErrorCode.SYSTEM_ERROR);
        }

        Map<String, Integer> lockRecord = new HashMap<String, Integer>();
        String notEnoughSku = "";
        boolean lockSuccess = true;
        for (OutboundItem item : header.getItemList()) {
            String sku = item.getSku();
            int qty = item.getQty();
            String key = RedisUtil.getSkuKey(customerId, warehouseId, sku);
            String lockSto = jedis.hget(key, RedisUtil.LOCK_STORAGE);
            int lockStoInt = ((lockSto == null ? 0 : Integer.parseInt(lockSto))) + qty;
            String sto = jedis.hget(key, RedisUtil.STORAGE);
            //校验库存是否足够
            if (sto == null || lockStoInt > Integer.parseInt(sto)) {
                notEnoughSku = sku;
                lockSuccess = false;
                break;
            }
            //记录锁定
            lockRecord.put(key, qty);
            jedis.hset(key, RedisUtil.LOCK_STORAGE, "" + lockStoInt);
        }


        if (!lockSuccess) {
            //锁定库存失败回滚
            for (Map.Entry<String, Integer> entry : lockRecord.entrySet()) {
                String key = entry.getKey();
                int qty = entry.getValue();
                String lObj = jedis.hget(key, RedisUtil.LOCK_STORAGE);
                int lInt = Integer.parseInt(lObj) - qty;
                jedis.hset(key, RedisUtil.LOCK_STORAGE, "" + lInt);
            }

        }
        RedisUtil.releaseDistributedLock(jedis, LOCK_KEY, requestId);
        if (!lockSuccess) {
            throw new WMSException(BizErrorCode.STORAGE_NOT_ENOUGH, notEnoughSku);
        }
        //锁定成功，添加订单到锁定列表
        for (OutboundItem item : header.getItemList()) {
            RedisUtil.hset(orderNoKey, item.getSku(), "" + item.getQty());
        }
    }


    @Override
    public void unLockStorage(String orderNo, String customerId, String warehouseId) {

        //check
        AssertUtil.isNotBlank(orderNo, CheckErrorCode.CLIENT_ORDER_EMPTY);
        AssertUtil.isNotBlank(customerId, CheckErrorCode.CUSTOMER_EMPTY);
        AssertUtil.isNotBlank(warehouseId, CheckErrorCode.WAREHOUSE_EMPTY);

        customerId = customerId.toLowerCase();
        warehouseId = warehouseId.toLowerCase();

        // 判断订单号是否已锁定
        String orderNoKey = RedisUtil.getLockOrderKey(customerId, warehouseId, orderNo);
        boolean keyExist = RedisUtil.hasKey(orderNoKey);
        if (!keyExist) {
            return;
        }

        //获取redis锁
        Jedis jedis = RedisUtil.getResource();
        String requestId = UUID.randomUUID().toString();
        boolean getLock = RedisUtil.tryGetDistributedLock(jedis, LOCK_KEY, requestId);
        if (!getLock) throw new WMSException(SysErrorCode.SYSTEM_ERROR);

        // 查询锁定列表
        Set<String> skuList = jedis.hkeys(orderNoKey);
        for (String sku : skuList) {
            int qty = Integer.parseInt(jedis.hget(orderNoKey, sku));
            String key = RedisUtil.getSkuKey(customerId, warehouseId, sku);
            String lockSto = jedis.hget(key, RedisUtil.LOCK_STORAGE);
            int afterLockStorage = Integer.parseInt(lockSto) - qty;
            jedis.hset(key, RedisUtil.LOCK_STORAGE, "" + afterLockStorage);
        }

        RedisUtil.releaseDistributedLock(jedis, LOCK_KEY, requestId);

        RedisUtil.del(orderNoKey);

    }

    @Override
    public void successStorage(String orderNo, String customerId, String warehouseId) {
        //check
        AssertUtil.isNotBlank(orderNo, CheckErrorCode.CLIENT_ORDER_EMPTY);
        AssertUtil.isNotBlank(customerId, CheckErrorCode.CUSTOMER_EMPTY);
        AssertUtil.isNotBlank(warehouseId, CheckErrorCode.WAREHOUSE_EMPTY);

        customerId = customerId.toLowerCase();
        warehouseId = warehouseId.toLowerCase();

        // 判断订单号是否已锁定
        String orderNoKey = RedisUtil.getLockOrderKey(customerId, warehouseId, orderNo);
        boolean keyExist = RedisUtil.hasKey(orderNoKey);
        if (!keyExist) {
            return;
        }

        //获取redis锁
        Jedis jedis = RedisUtil.getResource();
        String requestId = UUID.randomUUID().toString();
        boolean getLock = RedisUtil.tryGetDistributedLock(jedis, LOCK_KEY, requestId);
        if (!getLock) throw new WMSException(SysErrorCode.SYSTEM_ERROR);

        // 查询锁定列表
        Set<String> skuList = jedis.hkeys(orderNoKey);
        for (String sku : skuList) {
            int qty = Integer.parseInt(jedis.hget(orderNoKey, sku));

            //扣减锁定库存
            String key = RedisUtil.getSkuKey(customerId, warehouseId, sku);
            String lockSto = jedis.hget(key, RedisUtil.LOCK_STORAGE);
            int afterLockStorage = Integer.parseInt(lockSto) - qty;
            jedis.hset(key, RedisUtil.LOCK_STORAGE, "" + afterLockStorage);

            //扣减库存
            String key2 = RedisUtil.getSkuKey(customerId, warehouseId, sku);
            String sto = jedis.hget(key2, RedisUtil.STORAGE);
            int stoInt = Integer.parseInt(sto) - qty;
            jedis.hset(key, RedisUtil.STORAGE, "" + stoInt);

        }

        RedisUtil.releaseDistributedLock(jedis, LOCK_KEY, requestId);

        RedisUtil.del(orderNoKey);
    }


}
