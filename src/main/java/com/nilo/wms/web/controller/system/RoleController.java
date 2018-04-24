package com.nilo.wms.web.controller.system;

import com.nilo.wms.dto.PageResult;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.service.system.RoleService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public PageResult<Role> list() {
        return new PageResult<>();
    }

    @RequiresPermissions("/role")
    @PostMapping()
    public ResultMap add(Role role) {
        return ResultMap.success();
    }

    @RequiresPermissions("/role")
    @PutMapping()
    public ResultMap update(Role role) {
        return ResultMap.success();
    }

    @PutMapping("/role/status")
    public ResultMap updateStatus(String roleId, int status) {
        return ResultMap.success();

    }

    @DeleteMapping("/role/{id}")
    public ResultMap delete(String roleId) {
        return ResultMap.success();
    }

}
