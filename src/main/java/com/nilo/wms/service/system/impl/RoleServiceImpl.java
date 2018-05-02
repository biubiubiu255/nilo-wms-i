package com.nilo.wms.service.system.impl;

import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.dao.platform.RoleDao;
import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageResult<Role> queryRoles(RoleParameter parameter) {

        List<Role> list = roleDao.queryBy(parameter);
        PageResult<Role> result = new PageResult<>();
        result.setData(list);
        result.setCount(list.size());
        return result;
    }

    @Override
    public void add(Role role) {

    }
    @Override
    public void update(Role role) {

        AssertUtil.isNotNull(role, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(role.getRoleId(), CheckErrorCode.ROLE_ID_EMPTY);

        roleDao.update(role);
    }

}
