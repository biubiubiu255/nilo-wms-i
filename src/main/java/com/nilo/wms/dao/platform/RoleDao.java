package com.nilo.wms.dao.platform;


import com.nilo.wms.common.BaseDao;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Long, Role> {

    List<Role> queryBy(RoleParameter parameter);

    int deleteByRoleId(String roleId);
}