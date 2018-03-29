package com.nilo.wms.dto;


import com.nilo.wms.common.enums.InterfaceStatusEnum;

/**
 * Created by ronny on 2017/8/30.
 */
public class InterfaceConfig {

    private String merchantId;

    private String method;

    private String op;

    private String url;

    private String requestMethod;

    private InterfaceStatusEnum status;

    public InterfaceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InterfaceStatusEnum status) {
        this.status = status;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getStatusCode() {
        if (this.status == null) {
            return null;
        }
        return this.status.getCode();
    }
}
