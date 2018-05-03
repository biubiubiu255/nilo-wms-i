package com.nilo.wms.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.dto.system.ZTree;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.service.system.RoleService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String list(String searchValue, String searchKey) {

        RoleParameter parameter = new RoleParameter();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        return roleService.queryRoles(parameter).toJson();
    }

    @PostMapping
    public String add(Role role) {
        return ResultMap.success().toJson();
    }

    @PutMapping
    public String update(Role role) {
        return ResultMap.success().toJson();
    }

    @PutMapping("/status")
    public String updateStatus(String roleId, int status) {

        Role role = new Role();
        role.setRoleId(roleId);
        role.setStatus(status);
        roleService.update(role);

        return ResultMap.success().toJson();

    }

    @DeleteMapping("/{id}")
    public String delete(String roleId) {
        return ResultMap.success().toJson();
    }

    /**
     * 角色权限菜单树
     */
    @GetMapping("/tree/{roleId}")
    public String listPermTree(@PathVariable("roleId") String roleId) {
        List<ZTree> list = permissionService.getPermissionTree(roleId);
        return ResultMap.success().put("zTree", list).toJson();
    }

    @PutMapping("/tree")
    public String savePermTree(String roleId, String permIds) {

        List<String> permissionList = JSONArray.parseArray(permIds, String.class);
        permissionService.updatePermissionTree(roleId, permissionList);
        return ResultMap.success().toJson();
    }
}
