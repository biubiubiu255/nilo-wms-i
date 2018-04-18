/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.nilo.wms.dao.flux.StorageDao;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import com.nilo.wms.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
public class SkuStorageInfoController extends BaseController {

    @Autowired
    private StorageDao storageDao;

    @RequestMapping(value = "/storageInfo.html", method = {RequestMethod.GET})
    public String doGet() {
        return "storage";
    }

    @RequestMapping(value = "/storageInfo.html", method = {RequestMethod.POST})
    @ResponseBody
    public String storageInfo(String clientCode, String sku) {

        Map<String, Object> map = new HashMap<>();

        String key = RedisUtil.getSkuKey(clientCode, sku);
        String storage = RedisUtil.hget(key, RedisUtil.STORAGE);
        String lockSto = RedisUtil.hget(key, RedisUtil.LOCK_STORAGE);

        if (lockSto != null && (Integer.parseInt(lockSto)) > 0) {
            List<String> lockList = new ArrayList<>();
            Set<String> lockOrder = RedisUtil.keys("kilimall_ke01_lock_order_*");
            for (String order : lockOrder) {
                if (RedisUtil.hhasKey(order, sku)) {
                    Map<String, String> lockMap = new HashMap<>();
                    lockMap.put("Order", order);
                    lockMap.put("QTY:", RedisUtil.hget(order, sku));
                    lockList.add(JSON.toJSONString(lockMap));
                }
            }
            map.put("Lock_Order_List", lockList);
        }

        StorageParam param = new StorageParam();
        param.setSku(Arrays.asList(sku));
        List<StorageInfo> list = storageDao.queryBy(param);
        if (list != null && list.size() == 1) {
            map.put("wmsStorage", list.get(0).getStorage());
        }
        map.put("Storage", storage);
        map.put("LockStorage", lockSto);

        return JSON.toJSONString(map);

    }


}
