package com.nilo.wms.web.controller.system;

import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.DateUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.common.util.TokenUtil;
import com.nilo.wms.dto.system.Permission;
import com.nilo.wms.dto.system.User;
import com.nilo.wms.service.system.PermissionService;
import com.nilo.wms.service.system.RedisUtil;
import com.nilo.wms.service.system.UserService;
import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.model.ResultMap;
import io.jsonwebtoken.Claims;
import io.leopard.web.captcha.CaptchaUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String index(String username, String password, String randomCode, HttpServletRequest request) {

        AssertUtil.isNotBlank(username, CheckErrorCode.USER_NAME_EMPTY);
        AssertUtil.isNotBlank(password, CheckErrorCode.PASSWORD_EMPTY);
        AssertUtil.isNotBlank(randomCode, CheckErrorCode.RANDOM_CODE_EMPTY);

        // 校验验证码
        String codeValidate = CaptchaUtil.getCode(request);
        if (!StringUtil.equalsIgnoreCase(randomCode, codeValidate)) {
            throw new WMSException(BizErrorCode.RANDOM_CODE_ERROR);
        }
        //username or password
        User user = userService.queryByUserName(username);
        if (user == null || !StringUtil.equalsIgnoreCase(DigestUtils.md5Hex(password), user.getPassword())) {
            throw new WMSException(BizErrorCode.USERNAME_PASSWORD_ERROR);
        }
        if (user.getStatus() != 1) {
            throw new IllegalStateException("Account status not allow login.");
        }


        String token = TokenUtil.createToken(user.getUserId(), DateUtil.getAppointDate(new Date(), 30));
        user.setPassword(null);
        user.setToken(token);

        RedisUtil.hset(RedisUtil.getUserKey(user.getUserId()), "roleId", user.getRoleId());

        return ResultMap.success().put("token", token).put("user", user).toJson();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout() {
        // 登出操作
        return ResultMap.success().toJson();
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public String menu(HttpServletRequest request) {
        Claims c = TokenUtil.parseToken(getToken(request));
        String userId = c.getSubject();
        List<Permission> list = permissionService.getMenusByUser(userId);
        return ResultMap.success().put("menus", list).toJson();
    }

}
