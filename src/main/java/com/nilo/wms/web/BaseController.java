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

    public static void main(String[] args) {
        OutboundHeader header = new OutboundHeader();
        header.setOrderNo("1000000011");
        header.setCustomerId("kilimall");
        header.setWarehouseId("ke01");

        OutboundItem item1 = new OutboundItem();
        item1.setSku("1001");
        item1.setQty(2);

        OutboundItem item2 = new OutboundItem();
        item2.setSku("1002");
        item2.setQty(3);
        List<OutboundItem> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);



        header.setItemList(list);

        System.out.println(JSON.toJSONString(header));

    }
}