package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by admin on 2018/3/22.
 */
public class FluxInbound {
    @JSONField(name = "wms_asn_no")
    private String wmsAsnNo;
    @JSONField(name = "asn_no")
    private String ansNo;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "status_desc")
    private String statusDesc;

    public String getWmsAsnNo() {
        return wmsAsnNo;
    }

    public void setWmsAsnNo(String wmsAsnNo) {
        this.wmsAsnNo = wmsAsnNo;
    }

    public String getAnsNo() {
        return ansNo;
    }

    public void setAnsNo(String ansNo) {
        this.ansNo = ansNo;
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
