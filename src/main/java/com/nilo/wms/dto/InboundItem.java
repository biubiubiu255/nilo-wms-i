package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Administrator on 2017/7/6.
 */
public class InboundItem {
    private String customerId;
    @JSONField(name = "line_no")
    private int lineNo;
    @JSONField(name = "sku")
    private String sku;
    @JSONField(name = "goods_num")
    private int qty;
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


    public String getCustomerId() {
        return customerId;
    }

    @XmlElement(name = "CustomerID")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "LineNo")
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    @XmlElement(name = "SKU")
    public String getSku() {
        return sku;
    }


    public void setSku(String sku) {
        this.sku = sku;
    }

    @XmlElement(name = "ExpectedQty")
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
