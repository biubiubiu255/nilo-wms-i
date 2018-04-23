/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.web.controller;

import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.flux.StorageDao;
import com.nilo.wms.dto.ClientConfig;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import com.nilo.wms.service.RedisUtil;
import com.nilo.wms.service.config.SystemConfig;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;


@Controller
public class SkuStorageInfoController extends BaseController {

    @Autowired
    private StorageDao storageDao;

    @RequestMapping(value = "/storage.html", method = {RequestMethod.GET})
    public String doGet() {
        return "storage";
    }

    @RequestMapping(value = "/storageInfo.html", method = {RequestMethod.GET})
    @ResponseBody
    public String storageInfo(String clientCode, String sku) {

        ClientConfig config = SystemConfig.getClientConfig().get(clientCode);
        if (config == null) {
            throw new WMSException(BizErrorCode.APP_KEY_NOT_EXIST);
        }

        String key = RedisUtil.getSkuKey(clientCode, sku);
        String storage = RedisUtil.hget(key, RedisUtil.STORAGE);
        String lockSto = RedisUtil.hget(key, RedisUtil.LOCK_STORAGE);

        Map<String, String> map = new HashMap<>();

        StorageParam param = new StorageParam();
        param.setSku(Arrays.asList(sku));
        List<StorageInfo> list = storageDao.queryBy(param);
        if (list != null && list.size() == 1) {
            map.put("wmsStorage", "" + list.get(0).getStorage());
        }
        map.put("redisStorage", storage);
        map.put("lockStorage", lockSto);

        return toJsonTrueData(map);
    }

    @RequestMapping(value = "/lockList.html", method = {RequestMethod.GET})
    @ResponseBody
    public String lockList(String clientCode, String sku) {

        if (StringUtil.isEmpty(clientCode) || StringUtil.isEmpty(sku)) {
            return toPaginationLayUIData(0, 50, null);
        }

        String lockSto = RedisUtil.hget(RedisUtil.getSkuKey(clientCode, sku), RedisUtil.LOCK_STORAGE);

        List<OrderLock> lockList = new ArrayList<>();
        if (lockSto != null && (Integer.parseInt(lockSto)) > 0) {

            String lockKey = RedisUtil.getLockOrderKey(clientCode, "*");

            Set<String> lockOrderList = RedisUtil.keys(lockKey);

            for (String order : lockOrderList) {
                if (RedisUtil.hhasKey(order, sku)) {
                    OrderLock o = new OrderLock();
                    o.setOrderNo(order);
                    o.setQty(RedisUtil.hget(order, sku));
                    o.setTime(RedisUtil.hget(order, RedisUtil.LOCK_TIME));
                    lockList.add(o);
                }
            }
        }

        return toPaginationLayUIData(lockList.size(), 50, lockList);
    }

    @RequestMapping(value = "/updateStorage.html", method = {RequestMethod.POST})
    @ResponseBody
    public String updateStorage(String clientCode, String sku, Integer redisStorage, Integer lockStorage) {

        AssertUtil.isNotBlank(clientCode, CheckErrorCode.REQUEST_ID_EMPTY);
        AssertUtil.isNotBlank(sku, CheckErrorCode.REQUEST_ID_EMPTY);
        AssertUtil.isNotNull(redisStorage, CheckErrorCode.REQUEST_ID_EMPTY);
        AssertUtil.isNotNull(lockStorage, CheckErrorCode.REQUEST_ID_EMPTY);
        ClientConfig config = SystemConfig.getClientConfig().get(clientCode);
        if (config == null) {
            throw new WMSException(BizErrorCode.APP_KEY_NOT_EXIST);
        }

        //获取redis锁
        Jedis jedis = RedisUtil.getResource();
        String requestId = UUID.randomUUID().toString();
        boolean getLock = RedisUtil.tryGetDistributedLock(jedis, RedisUtil.LOCK_KEY, requestId);
        if (!getLock) throw new WMSException(SysErrorCode.SYSTEM_ERROR);

        RedisUtil.hset(RedisUtil.getSkuKey(clientCode, sku), RedisUtil.LOCK_STORAGE, "" + lockStorage);
        RedisUtil.hset(RedisUtil.getSkuKey(clientCode, sku), RedisUtil.STORAGE, "" + redisStorage);

        RedisUtil.releaseDistributedLock(jedis, RedisUtil.LOCK_KEY, requestId);

        return toJsonTrueMsg();
    }

    private static class OrderLock {
        private String orderNo;
        private String time;
        private String qty;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }
    }

}
