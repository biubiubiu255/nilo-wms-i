package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.parameter.PermissionParam;
import com.nilo.wms.dto.platform.system.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao extends BaseDao<Long, Permission> {

    List<Permission> queryByUserId(String userId);

    List<Permission> queryByRoleId(String roleId);

    int deletePermissionByRoleId(String roleId);

    List<Permission> queryAll();

    int insertRolePermission(@Param("permissionList") List<String> permissionList, @Param("roleId") String roleId);

    int queryPermissionsCount(PermissionParam parameter);

    List<Permission> queryPermissions(PermissionParam parameter);

    int deleteByPermissionId(String permissionId);
}