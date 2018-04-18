/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * SkuInfo
 *
 * @author Ronny.zeng
 * @version $Id: SkuInfo.java, v 0.1 2016年12月19日 下午3:10:17 Exp $
 */
@XmlRootElement(name = "header")
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 8155800852537298510L;
    private String customerId;
    @JSONField(name = "sku")
    private String sku;
    @JSONField(name = "goods_name")
    private String descC;
    @JSONField(name = "goods_name")
    private String descE;
    @JSONField(name = "store_id")
    private String storeId;
    @JSONField(name = "store_name")
    private String storeName;
    @JSONField(name = "price")
    private String price;
    @JSONField(name = "logistics_type")
    private String logisticsType;
    @JSONField(name = "class")
    private String freightClass;

    @XmlElement(name="CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    @XmlElement(name="SKU")
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    @XmlElement(name="Descr_C")
    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }
    @XmlElement(name="Descr_E")
    public String getDescE() {
        return descE;
    }

    public void setDescE(String descE) {
        this.descE = descE;
    }
    @XmlElement(name="SKU_Group7")
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    @XmlElement(name="ReservedField02")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    @XmlElement(name="Price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    @XmlElement(name="ReservedField01")
    public String getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(String logisticsType) {
        this.logisticsType = logisticsType;
    }
    @XmlElement(name="FreightClass")
    public String getFreightClass() {
        return freightClass;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }
}
