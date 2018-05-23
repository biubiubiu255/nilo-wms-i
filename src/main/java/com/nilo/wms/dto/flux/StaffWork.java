/**
 * Kilimall Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dto.flux;

/**
 * 
 * @author Ronny.zeng
 * @version $Id: StaffWorkDO.java, v 0.1 2016年10月7日 下午3:23:27 Exp $
 */
public class StaffWork {

    private String  userId;

    private String  date;

    private String  logisticsType;

    private Integer num;

    private Integer orderNum;

    private Integer skuNum;

    private String  orderNo;

    private String  price;

    private String  driver;

    private String  carrier;
        
    private String customerName;
    

    /**
     * Getter method for property <tt>customerName</tt>.
     * 
     * @return property value of customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Setter method for property <tt>customerName</tt>.
     * 
     * @param customerName value to be assigned to property customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Getter method for property <tt>driver</tt>.
     * 
     * @return property value of driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Setter method for property <tt>driver</tt>.
     * 
     * @param driver value to be assigned to property driver
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * Getter method for property <tt>carrier</tt>.
     * 
     * @return property value of carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Setter method for property <tt>carrier</tt>.
     * 
     * @param carrier value to be assigned to property carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * Getter method for property <tt>orderNo</tt>.
     * 
     * @return property value of orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * Setter method for property <tt>orderNo</tt>.
     * 
     * @param orderNo value to be assigned to property orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * Getter method for property <tt>price</tt>.
     * 
     * @return property value of price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter method for property <tt>price</tt>.
     * 
     * @param price value to be assigned to property price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Getter method for property <tt>orderNum</tt>.
     * 
     * @return property value of orderNum
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * Setter method for property <tt>orderNum</tt>.
     * 
     * @param orderNum value to be assigned to property orderNum
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * Getter method for property <tt>skuNum</tt>.
     * 
     * @return property value of skuNum
     */
    public Integer getSkuNum() {
        return skuNum;
    }

    /**
     * Setter method for property <tt>skuNum</tt>.
     * 
     * @param skuNum value to be assigned to property skuNum
     */
    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    /**
     * Getter method for property <tt>userId</tt>.
     * 
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     * 
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>date</tt>.
     * 
     * @return property value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method for property <tt>date</tt>.
     * 
     * @param date value to be assigned to property date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method for property <tt>logisticsType</tt>.
     * 
     * @return property value of logisticsType
     */
    public String getLogisticsType() {
        return logisticsType;
    }

    /**
     * Setter method for property <tt>logisticsType</tt>.
     * 
     * @param logisticsType value to be assigned to property logisticsType
     */
    public void setLogisticsType(String logisticsType) {
        this.logisticsType = logisticsType;
    }

    /**
     * Getter method for property <tt>num</tt>.
     * 
     * @return property value of num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Setter method for property <tt>num</tt>.
     * 
     * @param num value to be assigned to property num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

}
