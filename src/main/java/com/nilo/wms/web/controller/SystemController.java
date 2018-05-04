/**
 * KILIMALL Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.web.controller;

import com.nilo.wms.common.Principal;
import com.nilo.wms.common.SessionLocal;
import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.dto.common.ClientConfig;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.config.SystemConfig;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SystemController extends BaseController {

    @Autowired
    private BasicDataService basicDataService;

    @RequestMapping(value = "/syncStock.html", method = {RequestMethod.GET})
    @ResponseBody
    public String syncStock(HttpServletRequest request) {
        String clientCode = request.getParameter("client_code");
        ClientConfig config = SystemConfig.getClientConfig().get(clientCode);
        if (config == null) {
            throw new WMSException(BizErrorCode.APP_KEY_NOT_EXIST);
        }
        //设置调用api主体信息
        Principal principal = new Principal();
        principal.setClientCode(clientCode);
        principal.setCustomerId(config.getCustomerId());
        principal.setWarehouseId(config.getWarehouseId());
        SessionLocal.setPrincipal(principal);
        basicDataService.syncStock(clientCode);
        return toJsonTrueMsg();
    }

}
