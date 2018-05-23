package com.nilo.wms.dao.platform;

import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.platform.parameter.RoleParam;
import com.nilo.wms.dto.platform.system.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Long, Role> {

    List<Role> queryBy(RoleParam parameter);

    int deleteByRoleId(String roleId);
}