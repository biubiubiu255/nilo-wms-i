package com.nilo.wms.dto;


import com.nilo.wms.common.BaseDo;

/**
 * Created by ronny on 2017/8/30.
 */
public class InboundDO extends BaseDo<Long> {

    private String customerId;
    private String warehouseId;
    private String asnNo;
    private String asnNo2;
    private String asnType;
    private int status;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public String getAsnNo2() {
        return asnNo2;
    }

    public void setAsnNo2(String asnNo2) {
        this.asnNo2 = asnNo2;
    }

    public String getAsnType() {
        return asnType;
    }

    public void setAsnType(String asnType) {
        this.asnType = asnType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
