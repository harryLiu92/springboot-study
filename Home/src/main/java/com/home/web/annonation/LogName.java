package com.home.web.annonation;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liuhao
 * @Date: 2018/6/7 11:12
 * @Description: 注入打印日志的指定名字
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface LogName {

    String value() default StringUtils.EMPTY;
}