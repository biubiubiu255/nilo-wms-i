package com.nilo.wms.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/2/6.
 */
public enum MethodEnum {
    STORAGE("nos.storage.info", "Storage Info "),
    STORAGE_DETAIL("nos.storage.detail", "Storage Detail "),
    SKU("nos.sku.info", "SKU Info"),
    CUSTOMER("nos.customer.info", "Customer Info "),
    OUTBOUND_INFO("nos.outbound.query", "Outbound query "),
    INBOUND_INFO("nos.inbound.query", "Inbound query "),
    CREATE_INBOUND("nos.inbound.create", "nos.inbound.create"),
    CANCEL_INBOUND("nos.inbound.cancel", "nos.inbound.cancel"),
    CREATE_OUTBOUND("nos.outbound.create", "nos.outbound.create"),
    CANCEL_OUTBOUND("nos.outbound.cancel", "nos.outbound.cancel"),
    OUTBOUND_STATUS_UPDATE("nos.outbound.status", "outbound.status"),
    LOCK_STORAGE("nos.storage.lock", "Storage Lock "),
    UN_LOCK_STORAGE("nos.storage.unlock", "Storage UnLock "),;
    private String code;
    private String value;

    private static Map<String, MethodEnum> map = new HashMap<String, MethodEnum>(10);

    static {
        MethodEnum[] enums = MethodEnum.values();
        for (MethodEnum e : enums) {
            map.put(e.getCode(), e);
        }
    }

    private MethodEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static MethodEnum getEnum(String code) {
        return map.get(code);
    }
}
