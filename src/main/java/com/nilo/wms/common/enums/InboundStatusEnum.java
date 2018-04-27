package com.nilo.wms.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public enum InboundStatusEnum {

    create(0, "创建订单","Create"),
    received_part(30,"部分收货","Partially Received"),
    received_complete(40,"完全收货","Completely Received"),
    cancelled(90, "订单取消","Cancelled"),
    closed(99, "订单完成","Closed"),
    ;

    private int code;

    private String desc_c;

    private String desc_e;

    private static Map<Integer, InboundStatusEnum> map = new HashMap<Integer, InboundStatusEnum>(10);

    static {
        InboundStatusEnum[] enums = InboundStatusEnum.values();
        for (InboundStatusEnum e : enums) {
            map.put(e.getCode(), e);
        }
    }

    private InboundStatusEnum(int code, String desc_c, String desc_e) {
        this.code = code;
        this.desc_c = desc_c;
        this.desc_e = desc_e;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc_c() {
        return desc_c;
    }

    public void setDesc_c(String desc_c) {
        this.desc_c = desc_c;
    }

    public String getDesc_e() {
        return desc_e;
    }

    public void setDesc_e(String desc_e) {
        this.desc_e = desc_e;
    }

    public static InboundStatusEnum getEnum(Integer code) {
        return map.get(code);
    }

}
