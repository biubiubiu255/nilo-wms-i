package com.nilo.wms.service.scheduler;

import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.common.enums.MoneyType;
import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.dto.ClientConfig;
import com.nilo.wms.dto.Fee;
import com.nilo.wms.service.FeeService;
import com.nilo.wms.service.config.SystemConfig;
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
public class InBoundHandlerFeeScheduler  {
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

            List<Fee> list = feeService.queryInboundOrder(clientCode,dateString);
            feeService.syncToNOS(list, clientCode,dateString, MoneyType.In_Bound.getCode());
            logger.info("====end  inbound fee====");
        } catch (Exception ex) {
            logger.error("get delivery fee faild." ,ex.getMessage(),ex);
        }
    }
}
