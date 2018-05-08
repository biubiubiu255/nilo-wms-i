package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nilo.wms.common.enums.MethodEnum;
import com.nilo.wms.dto.*;
import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.flux.FluxInbound;
import com.nilo.wms.dto.flux.FluxOutbound;
import com.nilo.wms.dto.inbound.InboundHeader;
import com.nilo.wms.dto.outbound.OutboundHeader;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.InboundService;
import com.nilo.wms.service.OutboundService;
import com.nilo.wms.web.model.RequestParam;
import com.nilo.wms.web.model.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronny on 2017/8/30.
 */
@Controller
public class ApiController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OutboundService outBoundService;
    @Autowired
    private InboundService inboundService;
    @Autowired
    private BasicDataService basicDataService;

    @GetMapping("/api.html")
    public String doGet() {
        return "api";
    }

    @PostMapping("/api.html")
    @ResponseBody
    public String doPost(RequestParam param) {

        log.info("API Request:{}", param);

        param.checkParam();
        MethodEnum method = param.getMethod();
        String data = param.getData();

        switch (method) {
            case CREATE_OUTBOUND: {
                OutboundHeader outBound = JSON.parseObject(data, OutboundHeader.class);
                outBoundService.createOutBound(outBound);
                break;
            }
            case CANCEL_OUTBOUND: {
                String orderNo = JSON.parseObject(data).getString("client_ordersn");
                outBoundService.cancelOutBound(orderNo);
                break;
            }
            case CANCEL_INBOUND: {
                String orderNo = JSON.parseObject(data).getString("client_ordersn");
                inboundService.cancelInBound(orderNo);
                break;
            }
            case CREATE_INBOUND: {
                InboundHeader inbound = JSON.parseObject(data, InboundHeader.class);
                inboundService.createInBound(inbound);
                break;
            }
            case SKU: {
                List<SkuInfo> list = new ArrayList<>();
                if (data.startsWith("[")) {
                    list = JSONArray.parseArray(data, SkuInfo.class);
                } else {
                    SkuInfo skuInfo = JSON.parseObject(data, SkuInfo.class);
                    list.add(skuInfo);
                }
                basicDataService.updateSku(list);
                break;
            }
            case CUSTOMER: {
                List<SupplierInfo> list = new ArrayList<>();
                if (data.startsWith("[")) {
                    list = JSONArray.parseArray(data, SupplierInfo.class);
                } else {
                    SupplierInfo supplierInfo = JSON.parseObject(data, SupplierInfo.class);
                    list.add(supplierInfo);
                }
                basicDataService.updateSupplier(list);
                break;
            }
            case STORAGE: {
                StorageParam storageParam = JSON.parseObject(data, StorageParam.class);
                PageResult<StorageInfo> list = basicDataService.queryStorageDetail(storageParam);
                return ResultMap.success().put("response", list).toJson();
            }
            case OUTBOUND_INFO: {
                String orderNo = JSON.parseObject(data).getString("client_ordersn");
                FluxOutbound order = outBoundService.queryFlux(orderNo);
                return ResultMap.success().put("response", order).toJson();
            }
            case INBOUND_INFO: {
                String orderNo = JSON.parseObject(data).getString("client_ordersn");
                FluxInbound inbound = inboundService.queryFlux(orderNo);
                return ResultMap.success().put("response", inbound).toJson();
            }
            case LOCK_STORAGE: {
                OutboundHeader outBound = JSON.parseObject(data, OutboundHeader.class);
                basicDataService.lockStorage(outBound);
                break;
            }
            case UN_LOCK_STORAGE: {
                String orderNo = JSON.parseObject(data).getString("client_ordersn");
                basicDataService.unLockStorage(orderNo);
                break;
            }
            default:
                break;
        }
        return ResultMap.success().toJson();
    }

}
