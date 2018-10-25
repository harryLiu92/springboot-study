package com.liuhao.springboot.demo.custom;

import com.liuhao.springboot.demo.controller.ExceptionHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: liuhao
 * @Date: 2018/10/18 15:11
 * @Description:
 **/
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static Logger logger = LoggerFactory.getLogger(CustomErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
        Throwable exception = (Throwable)requestAttributes.getAttribute(ERROR_ATTRIBUTE, 0);
        logger.info("CustomErrorAttributes --> RuntimeException.class, e:{}", exception == null? "" : exception.getMessage());

        Map<String, Object> errorAttributes = new LinkedHashMap();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", 500);
        errorAttributes.put("message", "系统繁忙,请稍等....");

        return errorAttributes;
    }
}
