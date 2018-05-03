package com.nilo.wms.dto.system;

import java.io.Serializable;
import java.util.List;

public class Permission implements Serializable {
    private static final long serialVersionUID = -1080567663649018365L;

    private String permissionId;

    private String parentId;

    private String parentName;

    private String desc_c;

    private String desc_e;

    private String value;

    private String icon;

    private Integer type;

    private int status;

    private List<Permission> subMenus;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Permission> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Permission> subMenus) {
        this.subMenus = subMenus;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return this.desc_e + "-" + this.desc_c;
    }

}