/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.service.scheduler;

import com.nilo.wms.dto.SkuInfo;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import com.nilo.wms.dto.SupplierInfo;
import com.nilo.wms.service.BasicDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
            StorageParam param = new StorageParam();
            param.setCustomerId("KILIMALL");
            param.setWarehouseId("KE01");
            List<StorageInfo> list = basicDataService.queryStorage(param);
            if (list == null || list.size() == 0) return;



            logger.info(" ======= end SyncStockSchedule =======");
        } catch (Exception ex) {
            logger.error("SyncStockSchedule failed. {}", ex.getMessage(), ex);
        }
    }

}
