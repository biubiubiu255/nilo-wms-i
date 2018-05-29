package com.nilo.wms.service.scheduler;

import com.nilo.wms.common.enums.MoneyType;
import com.nilo.wms.dto.fee.Fee;
import com.nilo.wms.service.FeeService;
import com.nilo.wms.service.InboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 检测入库是否完毕
 * Created by Administrator on 2017/6/9.
 */
public class InBoundReviewsScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InboundService inboundService;

    public void execute() throws Throwable {
        inboundService.inboundScan();
    }
}
