package com.nilo.wms.dto.platform.storage;

import com.nilo.wms.common.BaseDo;

/**
 * 商品库存事物
 * Created by admin on 2018/5/10.
 */
public class StorageTransaction extends BaseDo<Long> {

    private String orderNo;

    private String type;

    private String fCustomerId;

    private String fWarehouseId;

    private String fSku;

    private String fBatchNo;

    private String fTrackNo;

    private String fLocation;

    private String fQty;

    private String tCustomerId;

    private String tWarehouseId;

    private String tSku;

    private String tBatchNo;

    private String tTrackNo;

    private String tLocation;

    private String tQty;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getfCustomerId() {
        return fCustomerId;
    }

    public void setfCustomerId(String fCustomerId) {
        this.fCustomerId = fCustomerId;
    }

    public String getfWarehouseId() {
        return fWarehouseId;
    }

    public void setfWarehouseId(String fWarehouseId) {
        this.fWarehouseId = fWarehouseId;
    }

    public String getfSku() {
        return fSku;
    }

    public void setfSku(String fSku) {
        this.fSku = fSku;
    }

    public String getfBatchNo() {
        return fBatchNo;
    }

    public void setfBatchNo(String fBatchNo) {
        this.fBatchNo = fBatchNo;
    }

    public String getfTrackNo() {
        return fTrackNo;
    }

    public void setfTrackNo(String fTrackNo) {
        this.fTrackNo = fTrackNo;
    }

    public String getfLocation() {
        return fLocation;
    }

    public void setfLocation(String fLocation) {
        this.fLocation = fLocation;
    }

    public String getfQty() {
        return fQty;
    }

    public void setfQty(String fQty) {
        this.fQty = fQty;
    }

    public String gettCustomerId() {
        return tCustomerId;
    }

    public void settCustomerId(String tCustomerId) {
        this.tCustomerId = tCustomerId;
    }

    public String gettWarehouseId() {
        return tWarehouseId;
    }

    public void settWarehouseId(String tWarehouseId) {
        this.tWarehouseId = tWarehouseId;
    }

    public String gettSku() {
        return tSku;
    }

    public void settSku(String tSku) {
        this.tSku = tSku;
    }

    public String gettBatchNo() {
        return tBatchNo;
    }

    public void settBatchNo(String tBatchNo) {
        this.tBatchNo = tBatchNo;
    }

    public String gettTrackNo() {
        return tTrackNo;
    }

    public void settTrackNo(String tTrackNo) {
        this.tTrackNo = tTrackNo;
    }

    public String gettLocation() {
        return tLocation;
    }

    public void settLocation(String tLocation) {
        this.tLocation = tLocation;
    }

    public String gettQty() {
        return tQty;
    }

    public void settQty(String tQty) {
        this.tQty = tQty;
    }
}
