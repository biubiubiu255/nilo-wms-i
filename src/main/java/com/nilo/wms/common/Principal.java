/*
 * Kilimall Inc.
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.nilo.wms.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Principal implements Serializable {

    private static final long serialVersionUID = 5798882004228239559L;

    private String clientCode;

    private String method;

    private String customerId;

    private String warehouseId;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

}