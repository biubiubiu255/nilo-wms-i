package com.nilo.wms.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
public enum OutBoundStatusEnum {

    create(0, "创建订单", "Create SO"),
    part_all(30, "部分分配", "Partially Allocated"),
    allocated(40, "分配完成", "Allocated"),
    part_picked(50, "部分拣货", "Partially Picked"),
    picked(60, "拣货完成", "Picked"),
    part_cart(62, "部分装箱", "Partially Cartonized"),
    cart(63, "完全装箱", "Cartonized"),
    part_load(65, "部分装车", "Partially Loaded"),
    load(66, "装车完成", "Loaded"),
    part_ship(70, "部分发运", "Partially Shipped"),
    ship(80, "完全发运", "Shipped"),
    cancelled(90, "订单取消", "Shipment Order Cancelled"),
    closed(99, "订单完成", "Shipment Order Closed"),;

    private int code;

    private String desc_c;

    private String desc_e;

    private static Map<Integer, OutBoundStatusEnum> map = new HashMap<Integer, OutBoundStatusEnum>(10);

    static {
        OutBoundStatusEnum[] enums = OutBoundStatusEnum.values();
        for (OutBoundStatusEnum e : enums) {
            map.put(e.getCode(), e);
        }
    }

    private OutBoundStatusEnum(int code, String desc_c, String desc_e) {
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

    public static OutBoundStatusEnum getEnum(Integer code) {
        return map.get(code);
    }

}
