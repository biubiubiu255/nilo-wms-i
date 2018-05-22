package com.nilo.wms.dto.platform.inbound;

import com.nilo.wms.common.BaseDo;


public class InboundDetail extends BaseDo<Integer> {

    private String asnNo;
    private Integer lineNo;
    private String  sku;
    private String  descE;
    private String  descC;
    private Integer expectedQty;
    private Integer receivedQty;
    private String  receivedLocation;
    private Short   status;

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescE() {
        return descE;
    }

    public void setDescE(String descE) {
        this.descE = descE;
    }

    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    public Integer getExpectedQty() {
        return expectedQty;
    }

    public void setExpectedQty(Integer expectedQty) {
        this.expectedQty = expectedQty;
    }

    public Integer getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(Integer receivedQty) {
        this.receivedQty = receivedQty;
    }

    public String getReceivedLocation() {
        return receivedLocation;
    }

    public void setReceivedLocation(String receivedLocation) {
        this.receivedLocation = receivedLocation;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InboundDetail{" +
                "asnNo='" + asnNo + '\'' +
                ", lineNo=" + lineNo +
                ", sku='" + sku + '\'' +
                ", descE='" + descE + '\'' +
                ", descC='" + descC + '\'' +
                ", expectedQty=" + expectedQty +
                ", receivedQty=" + receivedQty +
                ", receivedLocation='" + receivedLocation + '\'' +
                ", status=" + status +
                '}';
    }
}
