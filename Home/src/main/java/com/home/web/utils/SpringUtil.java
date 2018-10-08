package com.home.web.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Administrator liuhao
 * 2018/9/20 18:49
 **/
public class SpringUtil {

    private static ServletRequestAttributes requestAttributes;

    static {
        requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //        HttpServletResponse response = requestAttributes.getResponse();
    }

    public static void setSession(String key, Object value) {
        requestAttributes.getRequest().getSession().setAttribute(key, value);
    }

    public static Object getSession(String key) {
        return requestAttributes.getRequest().getSession().getAttribute(key);
    }
}
