package com.nilo.wms.web;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.nilo.wms.common.exception.WMSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronny on 2017/8/22.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();

        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "fail");
        if (ex instanceof WMSException) {
            WMSException e = (WMSException) ex;
            result.put("error", e.getMessage());
            result.put("msgid", e.getCode());
        } else if (ex instanceof Exception) {
            result.put("error", ex.getMessage());
            result.put("msgid", "99999");
        }

        logger.error("Request: Failed.", request.getRequestURI(), ex);

        view.setAttributesMap(result);
        mv.setView(view);

        return mv;
    }
}
