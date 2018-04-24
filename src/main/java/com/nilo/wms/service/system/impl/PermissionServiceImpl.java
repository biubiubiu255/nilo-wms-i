package com.nilo.wms.service.system.impl;

import com.nilo.wms.dao.platform.PermissionDao;
import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            p.setPermissionName(p.getNameE());

            if ("0".equals(p.getParentId())) {
                List<Permission> subMenu = new ArrayList<Permission>();
                for (Permission t : permissions) {
                    t.setPermissionName(t.getNameE());
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
}
