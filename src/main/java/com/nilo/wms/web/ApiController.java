package com.nilo.wms.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nilo.wms.common.enums.MethodEnum;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.dto.*;
import com.nilo.wms.service.BasicDataService;
import com.nilo.wms.service.InboundService;
import com.nilo.wms.service.OutboundService;
import com.nilo.wms.web.model.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

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

    @RequestMapping(value = "/api.html", method = RequestMethod.GET)
    public String doGet() {
        return "api";
    }

    @RequestMapping(value = "/api.html", method = RequestMethod.POST)
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
                String orderNo = JSON.parseObject(data).getString("client_order_sn");
                String customerId = JSON.parseObject(data).getString("client_id");
                customerId = "KILIMALL";
                String warehouseId = JSON.parseObject(data).getString("warehouse_id");
                outBoundService.cancelOutBound(orderNo);
                break;
            }
            case CANCEL_INBOUND: {
                String orderNo = JSON.parseObject(data).getString("client_order_sn");
                String customerId = JSON.parseObject(data).getString("client_id");
                customerId = "KILIMALL";
                String warehouseId = JSON.parseObject(data).getString("warehouse_id");
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
                SupplierInfo supplierInfo = JSON.parseObject(data, SupplierInfo.class);
                basicDataService.updateSupplier(supplierInfo);
                break;
            }
            case STORAGE: {
                StorageParam storageParam = JSON.parseObject(data, StorageParam.class);
                storageParam.setCustomerId("KILIMALL");
                List<StorageInfo> list = basicDataService.queryStorage(storageParam);
                return toJsonTrueData(list);
            }
            case STORAGE_DETAIL: {
                StorageParam storageParam = JSON.parseObject(data, StorageParam.class);
                storageParam.setCustomerId("KILIMALL");
                List<StorageInfo> list = basicDataService.queryStorageDetail(storageParam);
                return toJsonTrueData(list);
            }
            case OUTBOUND_INFO: {
                String orderNo = JSON.parseObject(data).getString("client_order_sn");
                String customerId = JSON.parseObject(data).getString("client_id");
                customerId = "KILIMALL";
                String warehouseId = JSON.parseObject(data).getString("warehouse_id");
                FluxOutbound order = outBoundService.queryFlux(orderNo);
                return toJsonTrueData(order);
            }
            case INBOUND_INFO: {
                String orderNo = JSON.parseObject(data).getString("client_order_sn");
                String customerId = JSON.parseObject(data).getString("client_id");
                customerId = "KILIMALL";
                String warehouseId = JSON.parseObject(data).getString("warehouse_id");
                FluxInbound inbound = inboundService.queryFlux(orderNo);
                return toJsonTrueData(inbound);
            }
            case LOCK_STORAGE: {
                OutboundHeader outBound = JSON.parseObject(data, OutboundHeader.class);
                basicDataService.lockStorage(outBound);
                break;
            }
            case UN_LOCK_STORAGE: {
                String orderNo = JSON.parseObject(data).getString("client_order_sn");
                String customerId = JSON.parseObject(data).getString("client_id");
                customerId = "KILIMALL";
                String warehouseId = JSON.parseObject(data).getString("warehouse_id");

                basicDataService.unLockStorage(orderNo, customerId, warehouseId);
                break;
            }
            default:
                break;
        }
        return toJsonTrueMsg();
    }

}
