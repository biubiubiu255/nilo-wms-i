package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by admin on 2018/3/21.
 */
public class StorageParam {

    private String customerId;
    private String warehouseId;
    @JSONField(name = "store_list")
    private List<String> storeId;
    @JSONField(name = "sku_list")
    private List<String> sku;

    private Integer page;

    private Integer offset;

    private Integer end;

    public Integer getEnd() {
        return limit * page;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCustomerId() {
        return this.customerId;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getOffset() {
        return limit * (page - 1) + 1;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
