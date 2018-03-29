package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by admin on 2018/3/21.
 */
public class StorageParam {

    @JSONField(name = "client_id")
    private String customerId;
    @JSONField(name = "warehouse_id")
    private String warehouseId;
    @JSONField(name = "store_list")
    private List<String> storeId;
    @JSONField(name = "sku_list")
    private List<String> sku;

    public String getCustomerId() {
        return "KILIMALL";
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<String> getStoreId() {
        return storeId;
    }

    public void setStoreId(List<String> storeId) {
        this.storeId = storeId;
    }

    public List<String> getSku() {
        return sku;
    }

    public void setSku(List<String> sku) {
        this.sku = sku;
    }
}
