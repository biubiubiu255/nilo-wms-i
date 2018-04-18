/*
 * Copyright 2015-2016 kilimall.com All rights reserved.
* Support: http://www.kilimall.co.ke

 */
package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.nilo.wms.dto.OutboundHeader;
import com.nilo.wms.dto.OutboundItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {


    protected final Logger logger = LoggerFactory.getLogger(getClass());


    protected ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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

    protected String removeXmlDataElement(String value, String removeEle) {
        return value.replace("<" + removeEle + ">", "").replace("</" + removeEle + ">", "");
    }
    protected String xmlSuccessReturn() {
        return "<xmldata><Response><return><returnCode>0000</returnCode><returnDesc>ok</returnDesc><returnFlag>1</returnFlag></return></Response></xmldata>";
    }

}