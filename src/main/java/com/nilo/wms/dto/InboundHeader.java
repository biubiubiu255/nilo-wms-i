package com.nilo.wms.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.nilo.wms.common.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@XmlRootElement(name = "header")
public class InboundHeader {
    private String customerId;
    @JSONField(name = "client_order_sn")
    private String referenceNo;
    @JSONField(name = "client_order_sn2")
    private String referenceNo2;
    @JSONField(name = "order_type")
    private String asnType;
    private String warehouseId;
    @JSONField(name = "add_time")
    private Long orderTime;
    @JSONField(name = "store_id")
    private String supplierId;
    @JSONField(name = "store_name")
    private String supplierName;
    @JSONField(name = "carrier_id")
    private String carrierId;
    @JSONField(name = "carrier_name")
    private String carrierName;
    @JSONField(name = "user_define1")
    private String userDefine1;
    @JSONField(name = "user_define2")
    private String userDefine2;
    @JSONField(name = "user_define3")
    private String userDefine3;
    @JSONField(name = "user_define4")
    private String userDefine4;
    @JSONField(name = "user_define5")
    private String userDefine5;

    @JSONField(name = "order_items_list")
    private List<InboundItem> itemList;

    @XmlElement(name = "detailsItem")
    public List<InboundItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<InboundItem> itemList) {
        this.itemList = itemList;
    }
    @XmlElement(name = "OrderNo")
    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
    @XmlElement(name = "ASNReference2")
    public String getReferenceNo2() {
        return referenceNo2;
    }

    public void setReferenceNo2(String referenceNo2) {
        this.referenceNo2 = referenceNo2;
    }

    @XmlElement(name = "OrderType")
    public String getAsnType() {
        return asnType;
    }

    public void setAsnType(String asnType) {
        this.asnType = asnType;
    }

    @XmlElement(name = "CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "WarehouseID")
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @XmlElement(name = "ASNCreationTime")
    public String getOrderTime() {
        if (orderTime == null) return null;
        return DateUtil.format(orderTime, DateUtil.LONG_WEB_FORMAT);
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }


    @XmlElement(name = "SupplierID")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @XmlElement(name = "Supplier_Name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @XmlElement(name = "CarrierID")
    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    @XmlElement(name = "CarrierName")
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @XmlElement(name = "UserDefine1")
    public String getUserDefine1() {
        return userDefine1;
    }

    public void setUserDefine1(String userDefine1) {
        this.userDefine1 = userDefine1;
    }

    @XmlElement(name = "UserDefine2")
    public String getUserDefine2() {
        return userDefine2;
    }

    public void setUserDefine2(String userDefine2) {
        this.userDefine2 = userDefine2;
    }

    @XmlElement(name = "UserDefine3")
    public String getUserDefine3() {
        return userDefine3;
    }

    public void setUserDefine3(String userDefine3) {
        this.userDefine3 = userDefine3;
    }

    @XmlElement(name = "UserDefine4")
    public String getUserDefine4() {
        return userDefine4;
    }

    public void setUserDefine4(String userDefine4) {
        this.userDefine4 = userDefine4;
    }

    @XmlElement(name = "UserDefine5")
    public String getUserDefine5() {
        return userDefine5;
    }

    public void setUserDefine5(String userDefine5) {
        this.userDefine5 = userDefine5;
    }
}
