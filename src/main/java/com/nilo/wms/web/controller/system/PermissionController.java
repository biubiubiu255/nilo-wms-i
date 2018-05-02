package com.nilo.wms.web.controller.system;

import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.web.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping()
    public String list( String searchKey, String searchValue){
        return null;
    }

    @GetMapping("/parent/{type}")
    public String listParent(@PathVariable("type") int type) {
        return null;
    }


    @PostMapping()
    public String add(Permission permission) {
        return ResultMap.success().toJson();
    }

    @PutMapping()
    public String update(Permission permission) {
        return ResultMap.success().toJson();
    }

    @PutMapping("status")
    public String updateStatus(String permissionId, int status) {
        return ResultMap.success().toJson();
    }

    @DeleteMapping("/{permissionId}")
    public String delete(@PathVariable("permissionId") String permissionId) {
        return ResultMap.success().toJson();
    }

}
