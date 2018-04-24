package com.nilo.wms.service.system;


import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.system.Role;

import java.util.List;


public interface RoleService {

    PageResult<Role> queryRoles();

    void add(Role role);

    void update(Role role);

}
