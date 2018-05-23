package com.nilo.wms.web.tag;

import com.nilo.wms.common.util.StringUtil;
import com.nilo.wms.common.util.TokenUtil;
import com.nilo.wms.service.platform.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class PermissionTag extends BodyTagSupport {

    private String name;

    @Override
    public int doStartTag() throws JspException {

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (StringUtil.isEmpty(token)) {
            return BodyTagSupport.SKIP_BODY;
        }
        String userId = TokenUtil.parseToken(token).getSubject();
        //判断是否有权限访问
        boolean exist = RedisUtil.hasPermission(userId, name);
        if (exist) {
            //允许访问标签body
            return BodyTagSupport.EVAL_BODY_INCLUDE;
        } else {
            return BodyTagSupport.SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
