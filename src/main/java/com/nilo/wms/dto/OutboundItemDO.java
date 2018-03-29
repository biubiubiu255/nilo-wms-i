package com.nilo.wms.dto;


import com.nilo.wms.common.BaseDo;

/**
 * Created by ronny on 2017/8/30.
 */
public class OutboundItemDO extends BaseDo<Long> {

    private String customerId;
    private String warehouseId;
    private String referenceNo;
    private String sku;
    private int qty;

    public String getCustomerId() {
        return customerId;
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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

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
