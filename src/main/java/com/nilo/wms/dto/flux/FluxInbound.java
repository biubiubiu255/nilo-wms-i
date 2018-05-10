package com.nilo.wms.dto.flux;

import com.alibaba.fastjson.annotation.JSONField;
import com.nilo.wms.common.enums.InboundStatusEnum;

import java.util.List;

/**
 * Created by admin on 2018/3/22.
 */
public class FluxInbound {
    @JSONField(name = "wms_order_sn")
    private String wmsAsnNo;
    @JSONField(name = "client_ordersn")
    private String referenceNo;
    @JSONField(name = "status")
    private int status;
    @JSONField(name = "status_desc")
    private String statusDesc;
    @JSONField(name = "order_items_list")
    private List<FluxInboundDetails> list;
    @JSONField(name = "weight")
    private Double weight;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<FluxInboundDetails> getList() {
        return list;
    }

    public void setList(List<FluxInboundDetails> list) {
        this.list = list;
    }

    public String getWmsAsnNo() {
        return wmsAsnNo;
    }

    public void setWmsAsnNo(String wmsAsnNo) {
        this.wmsAsnNo = wmsAsnNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

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
}
