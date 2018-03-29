package com.nilo.wms.web.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Administrator on 2017/7/27.
 */
public class NotifyOrderItem {
    private String sku;

    private int qty;

    public String getSku() {
        return sku;
    }
    @XmlElement(name = "SKU")
    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQty() {
        return qty;
    }
    @XmlElement(name = "ReceivedQty")
    public void setQty(int qty) {
        this.qty = qty;
    }
}
