package com.liuhao.springboot.demo.custom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: liuhao
 * @Date: 2018/10/16 10:50
 * @Description:
 **/
public class CustomLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String localeStr = request.getParameter("locale");

        Locale locale = Locale.getDefault();

        if (StringUtils.isNotBlank(localeStr)) {
            String[] localeArr  = localeStr.split("_");
            locale = new Locale(localeArr[0], localeArr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

}
