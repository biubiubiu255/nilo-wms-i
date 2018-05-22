package com.nilo.wms.dto.platform.rule;

import com.nilo.wms.common.BaseDo;

import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */
public class AllocateRule extends BaseDo<Integer> {

    private String customerId;

    private String warehouseCode;

    private Integer firstInFirstOut;

    private Integer cleanSkuFirst;

    private List<AllocateZoneRule> zoneList;

    public List<AllocateZoneRule> getZoneList() {
        return zoneList;
    }

    public void setZoneList(List<AllocateZoneRule> zoneList) {
        this.zoneList = zoneList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getFirstInFirstOut() {
        return firstInFirstOut;
    }

    public void setFirstInFirstOut(Integer firstInFirstOut) {
        this.firstInFirstOut = firstInFirstOut;
    }

    public Integer getCleanSkuFirst() {
        return cleanSkuFirst;
    }

    public void setCleanSkuFirst(Integer cleanSkuFirst) {
        this.cleanSkuFirst = cleanSkuFirst;
    }
}
