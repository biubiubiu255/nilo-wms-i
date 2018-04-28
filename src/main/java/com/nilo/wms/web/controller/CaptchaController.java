package com.nilo.wms.web.controller;

import com.nilo.wms.web.BaseController;
import com.nilo.wms.web.CaptchaEngineImpl;
import io.leopard.web.captcha.CaptchaView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

    @ResponseBody
    @RequestMapping("/image")
    public CaptchaView image() {
        return new CaptchaView(160, 70, CaptchaEngineImpl.class);
    }

}
