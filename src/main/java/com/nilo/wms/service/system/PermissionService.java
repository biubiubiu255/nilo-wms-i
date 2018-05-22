package com.nilo.wms.service.system;


import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.common.ZTree;
import com.nilo.wms.dto.platform.parameter.PermissionParam;
import com.nilo.wms.dto.system.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getMenusByUser(String userId);

    List<Permission> getPermissionByUser(String userId);

    List<ZTree> getPermissionTree(String roleId);

    void updatePermissionTree(String roleId, List<String> permissionId);

    void add(Permission permission);

    void update(Permission permission);

    void delete(String permissionId);

    PageResult<Permission> queryPermissions(PermissionParam parameter);
}
