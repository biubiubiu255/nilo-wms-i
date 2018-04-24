package com.nilo.wms.dto.system;

/**
 * 角色权限
 */
public class RolePermission {

    private String roleId;

    private String permission;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
