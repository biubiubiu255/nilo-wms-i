package com.nilo.wms.service.system.impl;

import com.nilo.wms.dao.platform.RoleDao;
import com.nilo.wms.dto.PageResult;
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
    public PageResult<Role> queryRoles() {
        return null;
    }

    @Override
    public void add(Role role) {

    }

    @Override
    public void update(Role role) {

    }


}
