/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dto;

/**
 * 富勒http请求实体VO
 * @author Ronny.zeng
 * @version $Id: FLuxHttpRequest.java, v 0.1 2016年6月21日 上午11:25:20 Exp $
 */
public class FLuxRequest {

    /** 接口名称 */
    private String method;
    /** 接口id */
    private String messageid;
    /** 数据格式，取值 json。xml */
    private String format="xml";
    /** XML 格式业务数据存放应用级参数数据 */
    private String data;


    /**
     * Getter method for property <tt>method</tt>.
     * 
     * @return property value of method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Setter method for property <tt>method</tt>.
     * 
     * @param method value to be assigned to property method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Getter method for property <tt>messageid</tt>.
     * 
     * @return property value of messageid
     */
    public String getMessageid() {
        return messageid;
    }

    /**
     * Setter method for property <tt>messageid</tt>.
     * 
     * @param messageid value to be assigned to property messageid
     */
    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    /**
     * Getter method for property <tt>format</tt>.
     * 
     * @return property value of format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Setter method for property <tt>format</tt>.
     * 
     * @param format value to be assigned to property format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Getter method for property <tt>data</tt>.
     * 
     * @return property value of data
     */
    public String getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     * 
     * @param data value to be assigned to property data
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FLuxRequest{" +
                "method='" + method + '\'' +
                ", messageid='" + messageid + '\'' +
                ", format='" + format + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
