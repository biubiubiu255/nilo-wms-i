package com.nilo.wms.web.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
public class NotifyOrder {
    private String orderNo;

    private String orderType;

    private String orderStatus;

    private String deliveryNo;

    private String customerId;

    private String warehouseId;

    private String udf01;

    private String udf02;

    private String udf03;

    private String udf04;

    private String udf05;

    private String udf06;

    private String udf08;

    private List<NotifyOrderItem> item;

    @XmlElement(name = "item")
    public List<NotifyOrderItem> getItem() {
        return item;
    }

    public void setItem(List<NotifyOrderItem> item) {
        this.item = item;
    }

    public String getOrderNo() {
        return orderNo;
    }

    @XmlElement(name = "OrderNo")
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    @XmlElement(name = "OrderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    @XmlElement(name = "Udf07")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    @XmlElement(name = "DeliveryNo")
    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "CustomerID")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    @XmlElement(name = "WarehouseID")
    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getUdf01() {
        return udf01;
    }

    @XmlElement(name = "Udf01")
    public void setUdf01(String udf01) {
        this.udf01 = udf01;
    }

    public String getUdf02() {
        return udf02;
    }

    @XmlElement(name = "Udf02")
    public void setUdf02(String udf02) {
        this.udf02 = udf02;
    }

    public String getUdf03() {
        return udf03;
    }

    @XmlElement(name = "Udf03")
    public void setUdf03(String udf03) {
        this.udf03 = udf03;
    }

    public String getUdf04() {
        return udf04;
    }

    @XmlElement(name = "Udf04")
    public void setUdf04(String udf04) {
        this.udf04 = udf04;
    }

    public String getUdf05() {
        return udf05;
    }

    @XmlElement(name = "Udf05")
    public void setUdf05(String udf05) {
        this.udf05 = udf05;
    }

    public String getUdf06() {
        return udf06;
    }

    @XmlElement(name = "Udf06")
    public void setUdf06(String udf06) {
        this.udf06 = udf06;
    }

    public String getUdf08() {
        return udf08;
    }

    @XmlElement(name = "Udf08")
    public void setUdf08(String udf08) {
        this.udf08 = udf08;
    }
}
