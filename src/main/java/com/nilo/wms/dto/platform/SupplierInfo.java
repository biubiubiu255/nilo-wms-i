/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dto.platform;

import com.alibaba.fastjson.annotation.JSONField;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 供应商
 *
 * @author Ronny.zeng
 * @version $Id: Supplier.ava, v 0.1 2016年12月19日 下午3:10:17 Exp $
 */
@XmlRootElement(name = "header")
public class SupplierInfo implements Serializable {

    /**  */
    private static final long serialVersionUID = 8155800852537298510L;
    @JSONField(name = "store_id")
    private String customerId;
    private String customerType = "VE";
    @JSONField(name = "store_name")
    private String descC;
    private String descE;
    @JSONField(name = "address")
    private String address;
    @JSONField(name = "contact")
    private String contact;
    @JSONField(name = "contact_phone")
    private String phone;
    @JSONField(name = "contact_email")
    private String email;
    @JSONField(name = "notes")
    private String notes;
    //1:China ; 2:Local
    @JSONField(name = "type")
    private String type;

    @XmlElement(name = "UDF1")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "CustomerID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name = "Customer_Type")
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @XmlElement(name = "Descr_C")
    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    @XmlElement(name = "Descr_E")
    public String getDescE() {
        return descC;
    }

    public void setDescE(String descE) {
        this.descE = descE;
    }

    @XmlElement(name = "Address1")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "Contact1")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @XmlElement(name = "Contact1_Tel1")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlElement(name = "Contact1_Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
