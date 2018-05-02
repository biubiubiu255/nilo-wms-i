package com.nilo.wms.service.system;


import com.nilo.wms.dto.system.ZTree;
import com.nilo.wms.dto.system.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getMenusByUser(String userId);

    List<ZTree> getPermissionTree(String roleId);

    void updatePermissionTree(String roleId, List<String> permissionId);

}
