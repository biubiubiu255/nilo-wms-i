package com.nilo.wms.dto.outbound;

import com.alibaba.fastjson.annotation.JSONField;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.ReceiverInfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.NONE)
public class OutboundHeader {
    private String customerId;
    private String warehouseId;

    private String orderNo;
    private String wmsNo;
    @JSONField(name = "add_time")
    private Long orderTime;
    @JSONField(name = "order_type")
    private String orderType;
    @JSONField(name = "waybill_number")
    private String deliveryNo;
    @JSONField(name = "delivery_type")
    private String channel;
    @JSONField(name = "order_amount")
    private double orderAmount;
    @JSONField(name = "is_pod")
    private String isCod;
    @JSONField(name = "pod_amount")
    private double codAmount;
    private String carrierId = "STANDARD";
    private String carrierName = "STANDARD";
    private String notes;
    private String voucher;
    @JSONField(name = "already_paid")
    private String amountReceived;
    @JSONField(name = "shipping_fee")
    private String deliveryFee;
    private String stop;
    private String receiverId = "XN";
    private String receiverName;
    private String receiverPhone;
    private String receiverCity;
    private String receiverAddress;
    @JSONField(name = "receiver_info")
    private ReceiverInfo receiverInfo;
    @JSONField(name = "order_items_list")
    private List<OutboundItem> itemList;

    public ReceiverInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
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
    @JSONField(name = "client_ordersn")
    public String getOrderNo() {
        return orderNo;
    }

    @XmlElement(name = "OrderNo")
    @JSONField(name = "client_ordersn")
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @XmlElement(name = "OrderTime")
    public String getOrderTime() {
        return DateUtil.format(orderTime, DateUtil.LONG_WEB_FORMAT);
    }


    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public String getReceiverId() {
        return receiverId;
    }

    @XmlElement(name = "ConsigneeID")
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    @XmlElement(name = "ConsigneeName")
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    @XmlElement(name = "C_Tel1")
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    @XmlElement(name = "C_City")
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    @XmlElement(name = "C_Address1")
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getCarrierId() {
        return carrierId;
    }

    @XmlElement(name = "CarrierId")
    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    @XmlElement(name = "CarrierName")
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @XmlElement(name = "OrderType")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @XmlElement(name = "Channel")
    public String getChannel() {
        if (StringUtil.equals(this.channel, "1")) {
            return "Y";
        } else {
            return "N";
        }
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @XmlElement(name = "H_EDI_02")
    public double getOrderAmount() {
        return orderAmount;
    }


    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    @XmlElement(name = "H_EDI_05")
    public String getIsCod() {
        if (StringUtil.equals(this.isCod, "1")) {
            return "Y";
        } else {
            return "N";
        }
    }


    public void setIsCod(String isCod) {
        this.isCod = isCod;
    }

    public double getCodAmount() {
        return codAmount;
    }

    @XmlElement(name = "H_EDI_06")
    public void setCodAmount(double codAmount) {
        this.codAmount = codAmount;
    }

    public String getNotes() {
        return notes;
    }

    @XmlElement(name = "UserDefine5")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OutboundItem> getItemList() {
        return itemList;
    }

    @XmlElement(name = "detailsItem")
    public void setItemList(List<OutboundItem> itemList) {
        this.itemList = itemList;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    @XmlElement(name = "UserDefine4")
    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getVoucher() {
        return voucher;
    }

    @XmlElement(name = "H_EDI_03")
    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getAmountReceived() {
        return amountReceived;
    }

    @XmlElement(name = "H_EDI_04")
    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    @XmlElement(name = "H_EDI_10")
    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getStop() {
        return stop;
    }

    @XmlElement(name = "Stop")
    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getWmsNo() {
        return wmsNo;
    }

    public void setWmsNo(String wmsNo) {
        this.wmsNo = wmsNo;
    }

}
