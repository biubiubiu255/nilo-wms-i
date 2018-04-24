package com.nilo.wms.service.scheduler;

import com.nilo.wms.common.enums.MoneyType;
import com.nilo.wms.dto.fee.Fee;
import com.nilo.wms.service.FeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 入仓费用
 * Created by Administrator on 2017/6/9.
 */
public class StorageFeeScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeeService feeService;

    public void execute() throws Throwable {
        try {
            logger.info("====start inbound fee====");
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            //查询入库
            String clientCode = "kilimall";
            List<Fee> list = feeService.queryStorageFee(clientCode, dateString);
            feeService.syncToNOS(list, clientCode, dateString, MoneyType.In_Bound.getCode());
            logger.info("====end  inbound fee====");
        } catch (Exception ex) {
            logger.error("get delivery fee faild.", ex.getMessage(), ex);
        }
    }
}
