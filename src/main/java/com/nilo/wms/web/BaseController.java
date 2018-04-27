/*
 * Copyright 2015-2016 kilimall.com All rights reserved.
* Support: http://www.kilimall.co.ke

 */
package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.control.Pagination;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        return token;
    }

    protected String toJsonTrueMsg() {
        Map<Object, Object> map = new HashMap<>();
        map.put("status", "succ");
        return JSON.toJSONString(map);
    }

    protected String toJsonTrueData(Object obj) {
        Map<Object, Object> map = new HashMap<>();
        map.put("status", "succ");
        map.put("response", obj);
        return JSON.toJSONString(map);
    }

    protected String toPaginationLayUIData(Integer totalCount, Integer limit, List data) {
        JSONObject jo = new JSONObject();
        jo.put("count", totalCount);
        jo.put("code", 0);
        jo.put("msg", 0);
        jo.put("data", data);
        jo.put("pages", totalCount / limit);
        return jo.toJSONString();
    }

    protected String removeXmlDataElement(String value, String removeEle) {
        return value.replace("<" + removeEle + ">", "").replace("</" + removeEle + ">", "");
    }

    protected String xmlSuccessReturn() {
        return "<xmldata><Response><return><returnCode>0000</returnCode><returnDesc>ok</returnDesc><returnFlag>1</returnFlag></return></Response></xmldata>";
    }

    protected Pagination getPage() {
        String _offset, _limit, _page;
        int offset = 0, limit = 10;
        HttpServletRequest req = getRequest();
        if ((_limit = req.getParameter("_size")) != null || (_limit = req.getParameter("length")) != null
                || (_limit = req.getParameter("limit")) != null) {
            limit = NumberUtils.toInt(_limit, 10);
        }
        if ((_offset = req.getParameter("_index")) != null || (_offset = req.getParameter("start")) != null
                || (_offset = req.getParameter("offset")) != null) {
            offset = NumberUtils.toInt(_offset);
        }
        if ((_page = req.getParameter("page")) != null) {
            int page = NumberUtils.toInt(_page);
            offset = limit * (page - 1);
        }
        return new Pagination(offset, limit);
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}