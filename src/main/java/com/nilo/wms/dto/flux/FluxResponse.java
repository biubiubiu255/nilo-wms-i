/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.dto.flux;

import com.nilo.wms.common.util.StringUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "return")
public class FluxResponse {

    private static final String success_flag = "1";

    private String returnCode;

    private String returnDesc;

    private String returnFlag;

    private String resultInfo;

    /**
     * Getter method for property <tt>returnCode</tt>.
     *
     * @return property value of returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * Setter method for property <tt>returnCode</tt>.
     *
     * @param returnCode value to be assigned to property returnCode
     */
    @XmlElement(name = "returnCode")
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * Getter method for property <tt>returnDesc</tt>.
     *
     * @return property value of returnDesc
     */
    public String getReturnDesc() {
        return returnDesc;
    }

    /**
     * Setter method for property <tt>returnDesc</tt>.
     *
     * @param returnDesc value to be assigned to property returnDesc
     */
    @XmlElement(name = "returnDesc")
    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    /**
     * Getter method for property <tt>returnFlag</tt>.
     *
     * @return property value of returnFlag
     */
    public String getReturnFlag() {
        return returnFlag;
    }

    /**
     * Setter method for property <tt>returnFlag</tt>.
     *
     * @param returnFlag value to be assigned to property returnFlag
     */
    @XmlElement(name = "returnFlag")
    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    /**
     * Getter method for property <tt>resultInfo</tt>.
     *
     * @return property value of resultInfo
     */
    @XmlElement(name = "resultInfo")
    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public boolean isSuccess() {
        if (StringUtil.equals(returnFlag, success_flag)) {
            return true;
        }
        return false;
    }
}
