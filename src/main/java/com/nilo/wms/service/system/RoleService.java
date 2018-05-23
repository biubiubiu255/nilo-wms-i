package com.nilo.wms.service.system;


import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.platform.parameter.RoleParam;
import com.nilo.wms.dto.platform.system.Role;


public interface RoleService {

    PageResult<Role> queryRoles(RoleParam parameter);

    void add(Role role);

    /**
     * 此接口只需要@@@
     * @param role
     */
    void update(Role role);

    void delete(String roleId);

}
