package com.nilo.wms.dto;

/**
 * Created by Administrator on 2017/6/9.
 */
public class OrderHandler {

    private String no;

    private String orderNo;

    private String orderType;

    private String categories;

    private String merchantId;

    private String merchantDes;

    private String sku;

    private int qty;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantDes() {
        return merchantDes;
    }

    public void setMerchantDes(String merchantDes) {
        this.merchantDes = merchantDes;
    }


}
