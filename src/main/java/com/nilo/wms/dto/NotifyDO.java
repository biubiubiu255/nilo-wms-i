package com.nilo.wms.dto;


import com.nilo.wms.common.BaseDo;

/**
 * Created by ronny on 2017/8/30.
 */
public class NotifyDO extends BaseDo<Long> {


    private String notifyId;

    private String url;

    private String param;

    private String result;

    private Integer status;

    private Integer num;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
