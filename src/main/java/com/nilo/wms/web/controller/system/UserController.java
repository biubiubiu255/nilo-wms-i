package com.nilo.wms.web.controller.system;

import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.service.system.UserService;
import com.nilo.wms.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

}
