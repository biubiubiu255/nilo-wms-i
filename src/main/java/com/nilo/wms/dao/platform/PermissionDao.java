package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.system.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao extends BaseDao<Long, Permission> {

    List<Permission> queryByUserId(String userId);

    List<Permission> queryAll();

    List<Permission> queryByRoleId(String roleId);

    long deletePermissionByRoleId(String roleId);

    long insertRolePermission(@Param("permissionList") List<String> permissionList, @Param("roleId") String roleId);

}