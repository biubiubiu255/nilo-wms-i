package com.nilo.wms.dto.platform.parameter;

import com.nilo.wms.dto.common.Page;

/**
 * Created by admin on 2018/4/26.
 */
public class SkuParam extends Page {

    private String desc;

    private String sku;

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
