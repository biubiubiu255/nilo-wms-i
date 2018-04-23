/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.service.scheduler;

import com.nilo.wms.service.BasicDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 安全库存任务
 */
public class SafeStockSchedule {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BasicDataService basicDataService;

    public void execute() {
        try {
            logger.info("====start SyncStockSchedule ====");
            String clientCode = "kilimall";
            basicDataService.syncStock(clientCode);
            logger.info(" ======= end SyncStockSchedule =======");
        } catch (Exception ex) {
            logger.error("SyncStockSchedule failed. {}", ex.getMessage(), ex);
        }
    }

}
