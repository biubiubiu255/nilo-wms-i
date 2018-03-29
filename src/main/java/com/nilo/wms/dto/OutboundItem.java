package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Administrator on 2017/4/27.
 */
public class OutboundItem {

    private String customerId="KILIMALL";
    @JSONField(name = "sku")
    private String sku;
    @JSONField(name = "goods_num")
    private Integer qty;
    @JSONField(name = "goods_price")
    private Double unitPrice;

    private int lineNo;

    public String getSku() {
        return sku;
    }

    @XmlElement(name = "SKU")
    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    @XmlElement(name = "QtyOrdered")
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    @XmlElement(name = "D_EDI_03")
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "CustomerID")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getLineNo() {
        return lineNo;
    }

    @XmlElement(name = "LineNo")
    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }



}
