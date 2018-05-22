package com.nilo.wms.dto.common;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class ResultMap extends HashMap<String, Object> {

    private ResultMap() {
    }

    public static ResultMap success() {
        ResultMap resultMap = new ResultMap();
        resultMap.put("status", "succ");
        return resultMap;
    }

    /**
     * 返回失败
     */
    public static ResultMap error() {
        return error("failed");
    }

    /**
     * 返回失败
     */
    public static ResultMap error(String message) {
        return error("9999", message);
    }

    /**
     * 返回失败
     */
    public static ResultMap error(String code, String message) {
        ResultMap resultMap = new ResultMap();
        resultMap.put("msgid", code);
        resultMap.put("error", message);
        resultMap.put("status", "failed");
        return resultMap;
    }

    /**
     * 放入object
     */
    @Override
    public ResultMap put(String key, Object object) {
        super.put(key, object);
        return this;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}