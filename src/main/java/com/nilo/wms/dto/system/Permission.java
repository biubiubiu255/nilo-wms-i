package com.nilo.wms.dto.system;

import java.io.Serializable;
import java.util.List;

public class Permission implements Serializable {
	private static final long serialVersionUID = -1080567663649018365L;

	private String permissionId;

    private String parentId;

    private String nameC;

    private String nameE;

    private String permissionName;

    private String permissionValue;

    private String permissionIcon;

    private Integer permissionType;

    private List<Permission> subMenus;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
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

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getPermissionIcon() {
        return permissionIcon;
    }

    public void setPermissionIcon(String permissionIcon) {
        this.permissionIcon = permissionIcon;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }
}