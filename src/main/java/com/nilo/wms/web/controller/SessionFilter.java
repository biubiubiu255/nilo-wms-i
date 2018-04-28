package com.nilo.wms.web.controller;

import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.common.util.TokenUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 2018/4/10.
 */
public class SessionFilter implements Filter {
    private static final String AJAX_HEADER_KEY = "X-Requested-With";

    private static final String ALLOW_URL = "";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        //排除登录请求
        if (uri.indexOf("api") != -1 || ALLOW_URL.indexOf(uri) != -1) {
            chain.doFilter(req, res);
            return;
        }
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
