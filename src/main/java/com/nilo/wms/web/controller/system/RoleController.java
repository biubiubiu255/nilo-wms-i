package com.nilo.wms.web.controller.system;

import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.service.system.RoleService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public String list(String searchValue, String searchKey) {

        RoleParameter parameter = new RoleParameter();
        if(StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        return roleService.queryRoles(parameter).toJson();
    }

    @PostMapping("/role")
    public String add(Role role) {
        return ResultMap.success().toJson();
    }

    @PutMapping("/role")
    public String update(Role role) {
        return ResultMap.success().toJson();
    }

    @PutMapping("/role/status")
    public String updateStatus(String roleId, int status) {

        Role role = new Role();
        role.setRoleId(roleId);
        role.setStatus(status);
        roleService.update(role);

        return ResultMap.success().toJson();

    }

    @DeleteMapping("/role/{id}")
    public String delete(String roleId) {
        return ResultMap.success().toJson();
    }

}
