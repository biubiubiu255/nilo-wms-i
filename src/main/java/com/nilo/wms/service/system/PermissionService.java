package com.nilo.wms.service.system;


import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.parameter.PermissionParameter;
import com.nilo.wms.dto.common.ZTree;
import com.nilo.wms.dto.system.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getMenusByUser(String userId);

    List<ZTree> getPermissionTree(String roleId);

    void updatePermissionTree(String roleId, List<String> permissionId);

    void add(Permission permission);

    void update(Permission permission);

    PageResult<Permission> queryPermissions(PermissionParameter parameter);
}
