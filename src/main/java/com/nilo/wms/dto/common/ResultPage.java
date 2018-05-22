package com.nilo.wms.dto.common;

import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class ResultPage  {

    private Integer count;
    private List list;

    public static ResultPage getResultPage(){
        ResultPage result = new ResultPage();
        return result;
    }

    public ResultPage setCount(Integer count){
        this.count = count;
        return this;
    }

    public ResultPage setData(List data){
        this.list = data;
        return this;
    }

    public  String toJSON(){
        JSONObject jo = new JSONObject();
        jo.put("count", count);
        jo.put("code", 0);
        jo.put("msg", 0);
        jo.put("data", list);
        return jo.toJSONString();

    }

}
