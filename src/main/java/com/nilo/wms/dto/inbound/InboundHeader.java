package com.nilo.wms.dto.inbound;

import com.alibaba.fastjson.annotation.JSONField;
import com.nilo.wms.common.util.DateUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@XmlRootElement(name = "header")
public class InboundHeader {
    private String customerId;
    private String referenceNo;
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
    @JSONField(name = "deliver_name")
    private String carrierName;

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
    @JSONField(name = "client_ordersn")
    public String getReferenceNo() {
        return referenceNo;
    }
    @JSONField(name = "client_ordersn")
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    @XmlElement(name = "ASNReference2")
    @JSONField(name = "client_ordersn2")
    public String getReferenceNo2() {
        return referenceNo2;
    }
    @JSONField(name = "client_ordersn2")
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

}
