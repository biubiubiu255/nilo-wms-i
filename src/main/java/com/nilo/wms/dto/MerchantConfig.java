package com.nilo.wms.dto;

import java.util.Map;

/**
 * Created by ronny on 2017/8/30.
 */
public class MerchantConfig {

    private String merchantId;

    private String key;

    private String merchantCode;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    private Map<String,InterfaceConfig> map;

    public Map<String, InterfaceConfig> getMap() {
        return map;
    }

    public void setMap(Map<String, InterfaceConfig> map) {
        this.map = map;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
