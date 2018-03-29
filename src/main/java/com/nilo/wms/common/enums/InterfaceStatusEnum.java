package com.nilo.wms.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronny on 2017/8/22.
 */
public enum InterfaceStatusEnum {
    NORMAL(1, "Normal"),
    DISABLED(2, "Disabled");
    private Integer code;
    private String desc;

    private static Map<Integer, InterfaceStatusEnum> map = new HashMap<Integer, InterfaceStatusEnum>(10);

    static {
        InterfaceStatusEnum[] enums = InterfaceStatusEnum.values();
        for (InterfaceStatusEnum e : enums) {
            map.put(e.getCode(), e);
        }
    }

    private InterfaceStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static InterfaceStatusEnum getEnum(Integer code) {
        return map.get(code);
    }

    public Map getMap() {
        return map;
    }
}
