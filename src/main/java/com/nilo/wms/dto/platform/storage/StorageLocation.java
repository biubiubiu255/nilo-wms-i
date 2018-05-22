package com.nilo.wms.dto.platform.storage;

import com.nilo.wms.common.BaseDo;

/**
 * 商品库存库位明细
 * Created by admin on 2018/5/10.
 */
public class StorageLocation extends BaseDo<Long> {

    private String sku;

    private String location;

    private String batchNo;

    private String trackNo;

    private Integer qty;

    private Integer qtyAllocated;

    private String customerId;

    private String warehouseId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQtyAllocated() {
        return qtyAllocated;
    }

    public void setQtyAllocated(Integer qtyAllocated) {
        this.qtyAllocated = qtyAllocated;
    }
}
