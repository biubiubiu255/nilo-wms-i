package com.nilo.wms.service.config;

import com.nilo.wms.dto.FeePrice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronny on 2017/8/30.
 */
public class SystemConfig {
    //仓库费用配置
    private static Map<String,FeePrice> feeConfig = new HashMap<>();

    public static Map<String, FeePrice> getFeeConfig() {
        return feeConfig;
    }

    public static void setFeeConfig(Map<String, FeePrice> feeConfig) {
        SystemConfig.feeConfig = feeConfig;
    }
}
