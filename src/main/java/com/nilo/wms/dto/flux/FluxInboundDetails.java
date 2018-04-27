package com.nilo.wms.dto.flux;

import com.alibaba.fastjson.annotation.JSONField;
import com.nilo.wms.common.enums.InboundStatusEnum;
import com.nilo.wms.common.util.DateUtil;

import java.util.List;

/**
 * Created by admin on 2018/3/22.
 */
public class FluxInboundDetails {
    @JSONField(name = "sku")
    private String sku;
    @JSONField(name = "goods_num")
    private Integer qty;
    @JSONField(name = "received_num")
    private Integer receivedQty;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "status_desc")
    private String statusDesc;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return InboundStatusEnum.getEnum(status).getDesc_e();
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(Integer receivedQty) {
        this.receivedQty = receivedQty;
    }
}
