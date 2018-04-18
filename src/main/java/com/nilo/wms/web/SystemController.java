/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.nilo.wms.dao.flux.StorageDao;
import com.nilo.wms.dto.StorageInfo;
import com.nilo.wms.dto.StorageParam;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/security")
public class SystemController extends BaseController {

    @Autowired
    private BasicDataService basicDataService;

    @RequestMapping(value = "/syncStock.html", method = {RequestMethod.GET})
    @ResponseBody
    public String syncStock(HttpServletRequest request) {
        String clientCode = request.getParameter("client_code");
        basicDataService.syncStock(clientCode);
        return toJsonTrueMsg();
    }

}
