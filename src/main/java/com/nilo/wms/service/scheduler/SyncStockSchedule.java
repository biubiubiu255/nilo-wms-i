/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.service.scheduler;

import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.RedisUtil;
import com.nilo.wms.service.config.SystemConfig;
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

            String clientCode = "kilimall";
            //设置调用api主体信息
            ClientConfig config = SystemConfig.getClientConfig().get(clientCode);
            if (config == null) {
                throw new WMSException(BizErrorCode.APP_KEY_NOT_EXIST);
            }
            //设置调用api主体信息
            Principal principal = new Principal();
            principal.setClientCode(clientCode);
            principal.setCustomerId(config.getCustomerId());
            principal.setWarehouseId(config.getWarehouseId());
            SessionLocal.setPrincipal(principal);
            basicDataService.syncStock(clientCode);
            logger.info(" ======= end SyncStockSchedule =======");
        } catch (Exception ex) {
            logger.error("SyncStockSchedule failed. {}", ex.getMessage(), ex);
        }
    }

}
