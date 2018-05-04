package com.nilo.wms.dto.parameter;

import com.nilo.wms.dto.common.Page;

/**
 * Created by admin on 2018/4/26.
 */
public class PermissionParameter {

    private String desc;

    private Integer type;

    private Page page;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getOffset() {
        return this.page.getOffset();
    }

    public int getLimit() {
        return this.page.getLimit();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
