package com.nilo.wms.web.controller.system;

import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.parameter.PermissionParameter;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping()
    public String list(String searchKey, String searchValue) {

        PermissionParameter parameter = new PermissionParameter();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        parameter.setPage(getPage());
        return permissionService.queryPermissions(parameter).toJson();
    }

    @GetMapping("/parent/{type}")
    public String listParent(@PathVariable("type") int type) {
        PermissionParameter parameter = new PermissionParameter();
        parameter.setPage(getPage());
        parameter.setType(type);
        return permissionService.queryPermissions(parameter).toJson();
    }

    @PostMapping()
    public String add(Permission permission) {

        permissionService.add(permission);

        return ResultMap.success().toJson();
    }

    @PutMapping()
    public String update(Permission permission) {

        permissionService.update(permission);
        return ResultMap.success().toJson();
    }

    @PutMapping("status")
    public String updateStatus(String permissionId, int status) {
        Permission permission = new Permission();
        permission.setPermissionId(permissionId);
        permission.setStatus(status);
        permissionService.update(permission);
        return ResultMap.success().toJson();
    }

    @DeleteMapping("/{permissionId}")
    public String delete(@PathVariable("permissionId") String permissionId) {

        permissionService.delete(permissionId);

        return ResultMap.success().toJson();
    }

}
