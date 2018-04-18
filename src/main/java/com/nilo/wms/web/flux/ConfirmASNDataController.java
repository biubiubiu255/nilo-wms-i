/**
 * KILIMALL.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.nilo.wms.web.flux;

import com.nilo.wms.common.util.XmlUtil;
import com.nilo.wms.dto.InboundHeader;
import com.nilo.wms.dto.InboundItem;
import com.nilo.wms.service.InboundService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.NotifyOrder;
import com.nilo.wms.web.model.NotifyOrderItem;
import com.nilo.wms.web.model.WMSOrderNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfirmASNDataController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InboundService inboundService;

    @RequestMapping(value = "/confirmASNData.html", method = {RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String confirmASNData(String data) {
        data = URLDecoder.decode(data);
        if (logger.isDebugEnabled()) {
            logger.debug("Request confirmASNData.html -data:{}", data);
        }
        data = removeXmlDataElement(data,"xmldata");
        inboundService.confirmASN(buildASNInfo(data));
        return xmlSuccessReturn();

    }

    @RequestMapping(value = "/confirmTRASNData.html", method = {RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String confirmTRASNData(String data) {

        data = URLDecoder.decode(data);
        if (logger.isDebugEnabled()) {
            logger.debug("Request confirmTRASNData.html -data:{}", data);
        }
        data = removeXmlDataElement(data,"xmldata");
        inboundService.confirmASN(buildASNInfo(data));
        return xmlSuccessReturn();

    }
    private List<InboundHeader> buildASNInfo(String data) {

        WMSOrderNotify notify = XmlUtil.XMLToBean(data, WMSOrderNotify.class);

        List<InboundHeader> list = new ArrayList<>();
        for (NotifyOrder n : notify.getList()) {
            InboundHeader asn = new InboundHeader();
            asn.setReferenceNo(n.getOrderNo());
            List<InboundItem> itemList = new ArrayList<>();
            for (NotifyOrderItem i : n.getItem()) {
                InboundItem item = new InboundItem();
                item.setSku(i.getSku());
                item.setQty(i.getQty());
                itemList.add(item);
            }
            asn.setItemList(itemList);
            list.add(asn);
        }
        return list;
    }
}
