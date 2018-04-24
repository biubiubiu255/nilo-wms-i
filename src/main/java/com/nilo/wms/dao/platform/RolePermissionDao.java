package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.system.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionDao extends BaseDao<Long, RolePermission> {

    void deleteAllPermissionsByRoleId(String roleId);

    void saveRolePermission(List<RolePermission> list);
}
