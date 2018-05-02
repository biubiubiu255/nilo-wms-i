package com.nilo.wms.web.controller.system;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String list(String searchValue, String searchKey) {

        UserParameter parameter = new UserParameter();
        if (StringUtil.isNotBlank(searchKey)) {
            BeanUtils.setProperty(parameter, searchKey, searchValue);
        }
        parameter.setPage(getPage());
        return userService.queryUsers(parameter).toJson();
    }

    @PostMapping()
    public String add(User user) {
        return ResultMap.success().toJson();
    }

    @PutMapping()
    public String update(User user) {
        return ResultMap.success().toJson();
    }

    @PutMapping("/status")
    public String updateStatus(String userId, int status) {

        User user = new User();
        user.setUserId(userId);
        user.setStatus(status);
        userService.update(user);

        return ResultMap.success().toJson();

    }

    @DeleteMapping("/{id}")
    public String delete(String roleId) {
        return ResultMap.success().toJson();
    }

}
