package com.nilo.wms.service.platform.impl;

import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.SysErrorCode;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.IdWorker;
import com.nilo.wms.dao.platform.PermissionDao;
import com.nilo.wms.dao.platform.RoleDao;
import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.platform.parameter.RoleParam;
import com.nilo.wms.dto.platform.system.Permission;
import com.nilo.wms.dto.platform.system.Role;
import com.nilo.wms.service.platform.RedisUtil;
import com.nilo.wms.service.platform.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageResult<Role> queryRoles(RoleParam parameter) {

        List<Role> list = roleDao.queryBy(parameter);
        PageResult<Role> result = new PageResult<>();
        result.setData(list);
        result.setCount(list.size());
        return result;
    }

    @Override
    public void add(Role role) {
        AssertUtil.isNotNull(role, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(role.getRoleName(), CheckErrorCode.ROLE_NAME_EMPTY);
        role.setRoleId("" + IdWorker.getInstance().nextId());
        role.setStatus(1);
        roleDao.insert(role);

    }

    @Override
    public void update(Role role) {

        AssertUtil.isNotNull(role, SysErrorCode.REQUEST_IS_NULL);
        AssertUtil.isNotBlank(role.getRoleId(), CheckErrorCode.ROLE_ID_EMPTY);
        roleDao.update(role);

        //更新状态，
        if (role.getStatus() != null) {
            if (role.getStatus() == 0) {
                RedisUtil.del(RedisUtil.getRoleKey(role.getRoleId()));
            } else {
                List<Permission> list = permissionDao.queryByRoleId(role.getRoleId());
                RedisUtil.del(RedisUtil.getRoleKey(role.getRoleId()));
                if (list == null || list.size() == 0) return;
                Set<String> s = new HashSet<>();
                for (Permission p : list) {
                    s.add(p.getPermissionId());
                }
                RedisUtil.sAdd(RedisUtil.getRoleKey(role.getRoleId()), s.toArray(new String[list.size()]));
            }
        }

    }

    @Override
    public void delete(String roleId) {
        AssertUtil.isNotBlank(roleId, CheckErrorCode.ROLE_ID_EMPTY);
        roleDao.deleteByRoleId(roleId);

        RedisUtil.del(RedisUtil.getRoleKey(roleId));
    }

}
