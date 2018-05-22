package com.nilo.wms.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.common.ResultMap;
import com.nilo.wms.dto.common.ZTree;
import com.nilo.wms.dto.platform.parameter.RoleParam;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.service.system.RoleService;
import com.nilo.wms.web.BaseController;
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
    @RequiresPermissions("10021")
    public String list(String searchValue, String searchKey) {

        RoleParam parameter = new RoleParam();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        return roleService.queryRoles(parameter).toJson();
    }

    @PostMapping
    @RequiresPermissions("10022")
    public String add(Role role) {
        roleService.add(role);
        return ResultMap.success().toJson();
    }

    @PutMapping
    @RequiresPermissions("10023")
    public String update(Role role) {
        roleService.update(role);
        return ResultMap.success().toJson();
    }

    @PutMapping("/status")
    @RequiresPermissions("10023")
    public String updateStatus(String roleId, int status) {

        Role role = new Role();
        role.setRoleId(roleId);
        role.setStatus(status);
        roleService.update(role);

        return ResultMap.success().toJson();

    }

    @DeleteMapping("/{roleId}")
    @RequiresPermissions("10024")
    public String delete(@PathVariable("roleId") String roleId) {

        roleService.delete(roleId);
        return ResultMap.success().toJson();
    }

    /**
     * 角色权限菜单树
     */
    @GetMapping("/tree/{roleId}")
    @RequiresPermissions("10025")
    public String listPermTree(@PathVariable("roleId") String roleId) {
        List<ZTree> list = permissionService.getPermissionTree(roleId);
        return ResultMap.success().put("zTree", list).toJson();
    }

    @PutMapping("/tree")
    @RequiresPermissions("10026")
    public String savePermTree(String roleId, String permIds) {
        List<String> permissionList = JSONArray.parseArray(permIds, String.class);
        permissionService.updatePermissionTree(roleId, permissionList);
        return ResultMap.success().toJson();
    }
}
