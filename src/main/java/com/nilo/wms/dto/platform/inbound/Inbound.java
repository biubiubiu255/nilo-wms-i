package com.nilo.wms.dto.platform.inbound;

import com.nilo.wms.common.BaseDo;


public class Inbound extends BaseDo<Integer> {

    private String clientCode;
    private String customerCode;
    private String warehouseCode;
    private String asnNo;
    private String referenceNo;
    private String referenceNo2;
    private String asnType;
    private Integer  status;
    private String supplierId;
    private String supplierName;

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getAsnNo() {
        return asnNo;
    }

    public void setAsnNo(String asnNo) {
        this.asnNo = asnNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getReferenceNo2() {
        return referenceNo2;
    }

    public void setReferenceNo2(String referenceNo2) {
        this.referenceNo2 = referenceNo2;
    }

    public String getAsnType() {
        return asnType;
    }

    public void setAsnType(String asnType) {
        this.asnType = asnType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Inbound{" +
                "customerCode='" + customerCode + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", asnNo='" + asnNo + '\'' +
                ", referenceNo='" + referenceNo + '\'' +
                ", referenceNo2='" + referenceNo2 + '\'' +
                ", asnType='" + asnType + '\'' +
                ", status=" + status +
                ", supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
