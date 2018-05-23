package com.nilo.wms.dto.platform.parameter;

import com.nilo.wms.dto.common.Page;

/**
 * Created by admin on 2018/4/26.
 */
public class InventoryBalanceParam extends Page {

    private String sku;

    private String supplierId;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
