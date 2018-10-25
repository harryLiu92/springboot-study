package com.liuhao.springboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuhao
 * @Date: 2018/10/18 14:24
 * @Description:
 **/
@ControllerAdvice
public class ExceptionHandlerController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

//    @ExceptionHandler(RuntimeException.class)
//    public String handleHtmlException(Exception e, HttpServletRequest request){
//
//        logger.info("text/html --> RuntimeException.class, e:{}", e.getMessage());
//
//        Map<String,Object> map = new HashMap<String,Object>();
//        //传入我们自己的错误状态码  4xx 5xx
//        /**
//         * Integer statusCode = (Integer) request
//         .getAttribute("javax.servlet.error.status_code");
//         */
//        request.setAttribute("javax.servlet.error.status_code",500);
//        map.put("code","user.notexist");
//        map.put("message","用户出错啦");
//
//        request.setAttribute("ext",map);
//        //转发到/error
//        return "forward:/error";
//    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String handleJsonException(RuntimeException e){
        logger.info("ExceptionHandlerController --> RuntimeException.class, e:{}", e.getMessage());
        JSONObject result = new JSONObject();
        result.put("code", 500);
        result.put("msg", "系统繁忙,请稍等...");

        return result.toJSONString();
    }
}