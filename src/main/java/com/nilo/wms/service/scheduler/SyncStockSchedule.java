/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.service.scheduler;

import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.SkuInfo;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import com.nilo.wms.dto.SupplierInfo;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * 同步库存任务
 */
public class SyncStockSchedule {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BasicDataService basicDataService;

    public void execute() {
        try {
            logger.info("====start SyncStockSchedule ====");
            basicDataService.syncStock("KILIMALL", "KE01");
            logger.info(" ======= end SyncStockSchedule =======");
        } catch (Exception ex) {
            logger.error("SyncStockSchedule failed. {}", ex.getMessage(), ex);
        }
    }

}
