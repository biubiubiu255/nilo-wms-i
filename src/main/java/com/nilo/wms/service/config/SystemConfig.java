package com.nilo.wms.service.config;

import com.nilo.wms.dto.common.ClientConfig;
import com.nilo.wms.dto.fee.FeePrice;
import com.nilo.wms.dto.common.InterfaceConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronny on 2017/8/30.
 */
public class SystemConfig {
    //仓库费用配置
    private static Map<String ,Map<String,FeePrice>> feeConfig = new HashMap<>();

    //Client配置
    private static Map<String,ClientConfig> clientConfig = new HashMap<>();

    //Interface配置
    private static Map<String,Map<String,InterfaceConfig>> interfaceConfig = new HashMap<>();

    public static Map<String, Map<String, InterfaceConfig>> getInterfaceConfig() {
        return interfaceConfig;
    }

    public static void setInterfaceConfig(Map<String, Map<String, InterfaceConfig>> interfaceConfig) {
        SystemConfig.interfaceConfig = interfaceConfig;
    }

    public static Map<String, ClientConfig> getClientConfig() {
        return clientConfig;
    }

    public static void setClientConfig(Map<String, ClientConfig> clientConfig) {
        SystemConfig.clientConfig = clientConfig;
    }

    public static Map<String, Map<String, FeePrice>> getFeeConfig() {
        return feeConfig;
    }

    public static void setFeeConfig(Map<String, Map<String, FeePrice>> feeConfig) {
        SystemConfig.feeConfig = feeConfig;
    }
}
