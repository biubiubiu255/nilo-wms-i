package com.nilo.wms.service.system;


import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Role;


public interface RoleService {

    PageResult<Role> queryRoles(RoleParameter parameter);

    void add(Role role);

    void update(Role role);



}
