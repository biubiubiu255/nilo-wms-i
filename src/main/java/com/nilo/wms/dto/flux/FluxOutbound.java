package com.nilo.wms.dto.flux;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by admin on 2018/3/22.
 */
public class FluxOutbound {
    @JSONField(name = "wms_order_sn")
    private String wmsOrderNo;
    @JSONField(name = "client_order_sn")
    private String referenceNo;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "status_desc")
    private String statusDesc;
    @JSONField(name = "weight")
    private Double weight;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWmsOrderNo() {
        return wmsOrderNo;
    }

    public void setWmsOrderNo(String wmsOrderNo) {
        this.wmsOrderNo = wmsOrderNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
