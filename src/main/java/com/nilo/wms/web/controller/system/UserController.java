package com.nilo.wms.web.controller.system;

import com.nilo.wms.common.annotation.RequiresPermissions;
import com.nilo.wms.common.util.BeanUtils;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.dto.parameter.RoleParameter;
import com.nilo.wms.dto.parameter.UserParameter;
import com.nilo.wms.dto.system.Role;
import com.nilo.wms.dto.system.User;
import com.nilo.wms.service.system.RoleService;
import com.nilo.wms.service.system.UserService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("10011")
    public String list(String searchValue, String searchKey) {

        UserParameter parameter = new UserParameter();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        parameter.setPage(getPage());
        return userService.queryUsers(parameter).toJson();
    }

    @PostMapping
    @RequiresPermissions("10012")
    public String add(User user) {

        userService.add(user);

        return ResultMap.success().toJson();
    }

    @PutMapping
    @RequiresPermissions("10013")
    public String update(User user) {

        userService.update(user);

        return ResultMap.success().toJson();
    }

    @PutMapping("/status")
    @RequiresPermissions("10013")
    public String updateStatus(String userId, int status) {

        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        userService.update(user);

        return ResultMap.success().toJson();

    }

    @PutMapping("/psw/{userId}")
    @RequiresPermissions("10015")
    public String psw(@PathVariable("userId") String userId) {

        User user = new User();
        user.setUserId(userId);
        user.setPassword(DigestUtils.md5Hex("12345678"));
        userService.update(user);
        return ResultMap.success().toJson();
    }

    @DeleteMapping("/{userId}")
    @RequiresPermissions("10014")
    public String delete(@PathVariable("userId") String userId) {
        return ResultMap.success().toJson();
    }

}
