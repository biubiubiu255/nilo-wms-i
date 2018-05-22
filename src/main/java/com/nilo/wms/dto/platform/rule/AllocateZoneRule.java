package com.nilo.wms.dto.platform.rule;

import com.nilo.wms.common.BaseDo;

/**
 * Created by admin on 2018/5/11.
 */
public class AllocateZoneRule extends BaseDo<Integer> {

    private Integer allocateRuleId;

    private String orderType;

    private String zone;


    public Integer getAllocateRuleId() {
        return allocateRuleId;
    }

    public void setAllocateRuleId(Integer allocateRuleId) {
        this.allocateRuleId = allocateRuleId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
