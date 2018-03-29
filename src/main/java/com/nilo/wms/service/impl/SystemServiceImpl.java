package com.nilo.wms.service.impl;

import com.nilo.wms.dto.FeePrice;
import com.nilo.wms.service.SystemService;
import com.nilo.wms.service.config.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/7/6.
 */
@Service
public class SystemServiceImpl implements SystemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String fee_config_name = "wms_fee_config.properties";

    private final static int WORKING = 1;
    private final static int SUSPENDING = 0;
    private AtomicInteger customerCaching = new AtomicInteger(WORKING);

    /**
     * 刷新 wms 费用配置
     */
    @Override
    public void loadingAndRefreshWMSFeeConfig() {

        File file = new File(System.getProperty("user.home") + File.separator + fee_config_name);
        if (!file.exists()) {
            logger.warn("Config file is not exist at path of {}", file.getName());
            throw new RuntimeException("File is not exist");
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            //ignore
        }

        Enumeration enum1 = properties.propertyNames();
        Map<String, FeePrice> feeConfig = new HashMap<>();
        while (enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = properties.getProperty(strKey);
            FeePrice fee = new FeePrice();
            if (strValue.indexOf(",") != -1) {
                String[] str = strValue.split(",");
                BigDecimal first = BigDecimal.valueOf(Double.parseDouble(str[0]));
                BigDecimal next = BigDecimal.valueOf(Double.parseDouble(str[1]));

                fee.setFirstPrice(first);
                fee.setNextPrice(next);
            } else {
                BigDecimal first = BigDecimal.valueOf(Double.parseDouble(strValue));

                fee.setFirstPrice(first);
            }
            feeConfig.put(strKey, fee);
        }
        SystemConfig.setFeeConfig(feeConfig);
    }
}
