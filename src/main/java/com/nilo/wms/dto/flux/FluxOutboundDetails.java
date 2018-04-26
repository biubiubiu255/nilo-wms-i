package com.nilo.wms.dto.flux;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by admin on 2018/3/22.
 */
public class FluxOutboundDetails {

    private String sku;

    private int qty;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
