package com.nilo.wms.web.controller;

import com.nilo.wms.common.exception.BizErrorCode;
import com.nilo.wms.common.exception.CheckErrorCode;
import com.nilo.wms.common.exception.WMSException;
import com.nilo.wms.common.util.AssertUtil;
import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.web.BaseController;
import io.leopard.web.captcha.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    @ResponseBody
    public String index(String username, String password, String randomCode, HttpServletRequest request) {
        HttpSession session = request.getSession();

        AssertUtil.isNotBlank(username, CheckErrorCode.USER_NAME_EMPTY);
        AssertUtil.isNotBlank(password, CheckErrorCode.PASSWORD_EMPTY);
        AssertUtil.isNotBlank(randomCode, CheckErrorCode.RANDOM_CODE_EMPTY);

        // 校验验证码
        String codeValidate = CaptchaUtil.getCode(request);
        if (!StringUtil.equalsIgnoreCase(randomCode, codeValidate)) {
            throw new WMSException(BizErrorCode.RANDOM_CODE_ERROR);
        }
        return toJsonTrueMsg();
    }

    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 登出操作
        session.invalidate();
        return "redirect:/";
    }

}
