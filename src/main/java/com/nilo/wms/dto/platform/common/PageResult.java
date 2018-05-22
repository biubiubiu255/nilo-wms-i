package com.nilo.wms.dto.platform.common;

import com.alibaba.fastjson.JSON;

import java.util.List;


public class PageResult<T> {

    private int code=0;

    private String msg="";

    private int count;

    private List<T> data;

    public PageResult() {
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public PageResult(int total, List<T> rows) {
        this.count = total;
        this.data = rows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

}
