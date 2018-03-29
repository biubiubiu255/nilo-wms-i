package com.nilo.wms.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by admin on 2018/3/29.
 */
public class NiloRequest {

    private String method;
    @JSONField(name = "app_key")
    private String appKey;
    private String data;
    private String sign;
    @JSONField(name = "request_id")
    private String requestId;
    private Long timestamp;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
