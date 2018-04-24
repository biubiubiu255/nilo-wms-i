package com.nilo.wms.dto.flux;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FluxWeight {

    @JSONField(name = "waybill_number")
    private String waybillNum;

    private Double weight;

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
