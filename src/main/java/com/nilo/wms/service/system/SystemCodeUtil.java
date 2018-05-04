package com.nilo.wms.service.system;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

/**
 * Created by admin on 2017/10/27.
 */
public class SystemCodeUtil {

    public static List getSystemCodeList(String type) {

        String key = "wms_system_code_" + getLang() + "_" + type;
        Set<String> set = RedisUtil.hkeys(key);
        List<Map<String, String>> list = new ArrayList<>();
        for (String code : set) {
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            map.put("desc", RedisUtil.hget(key, code));
            list.add(map);
        }
        return list;
    }

    public static String getCodeDesc(String type, String code) {
        String key = "wms_system_code_" + getLang() + "_" + type;
        return RedisUtil.hget(key, code);
    }

    public static String getLang() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getLocale().getLanguage();
    }

}
