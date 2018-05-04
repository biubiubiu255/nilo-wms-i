package com.nilo.wms.service.system.impl;

import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dao.platform.PermissionDao;
import com.nilo.wms.dto.common.PageResult;
import com.nilo.wms.dto.parameter.PermissionParameter;
import com.nilo.wms.dto.common.ZTree;
import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.service.system.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronny on 2017/8/24.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> getMenusByUser(String userId) {

        List<Permission> results = new ArrayList<Permission>();
        List<Permission> permissions = permissionDao.queryByUserId(userId);
        for (Permission p : permissions) {
            if ("0".equals(p.getParentId())) {
                List<Permission> subMenu = new ArrayList<Permission>();
                for (Permission t : permissions) {
                    if (p.getPermissionId().equals(t.getParentId())) {
                        subMenu.add(t);
                    }
                }
                p.setSubMenus(subMenu);
                results.add(p);
            }
        }
        return results;
    }

    @Override
    public List<ZTree> getPermissionTree(String roleId) {

        List<ZTree> listZTree = new ArrayList<>();

        List<Permission> allPermissions = permissionDao.queryAll();
        List<Permission> rolePermissions = permissionDao.queryByRoleId(roleId);

        for (Permission one : allPermissions) {
            ZTree zTree = new ZTree();
            zTree.setId(one.getPermissionId());
            zTree.setName(one.getDesc_e() + "-" + one.getDesc_c());
            zTree.setpId(one.getParentId());
            zTree.setOpen(true);
            zTree.setChecked(false);
            for (Permission temp : rolePermissions) {
                if (StringUtil.equals(temp.getPermissionId(), one.getPermissionId())) {
                    zTree.setChecked(true);
                    break;
                }
            }
            listZTree.add(zTree);
        }
        return listZTree;
    }

    @Override
    @Transactional
    public void updatePermissionTree(String roleId, List<String> permissionId) {

        //1、删除
        permissionDao.deletePermissionByRoleId(roleId);
        //2、插入
        permissionDao.insertRolePermission(permissionId, roleId);

        //更新缓存中信息
        RedisUtil.del(RedisUtil.getRoleKey(roleId));
        RedisUtil.sAdd(RedisUtil.getRoleKey(roleId), permissionId.toArray(new String[permissionId.size()]));

    }

    @Override
    public void add(Permission permission) {

    }

    @Override
    public void update(Permission permission) {

    }

    @Override
    public PageResult<Permission> queryPermissions(PermissionParameter parameter) {

        PageResult<Permission> pageResult = new PageResult<>();

        int count = permissionDao.queryPermissionsCount(parameter);
        if (count == 0) {
            return pageResult;
        }
        pageResult.setCount(count);
        List<Permission> list = permissionDao.queryPermissions(parameter);
        pageResult.setData(list);
        return pageResult;
    }
}
