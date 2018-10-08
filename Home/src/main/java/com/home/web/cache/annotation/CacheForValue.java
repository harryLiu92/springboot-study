package com.home.web.cache.annotation;

import com.home.web.cache.constant.CacheValueOperationType;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 16:27
 * @Description:
 **/
public @interface CacheForValue {

    CacheValueOperationType operation();

    String key() default "";

    long timeout() default 0L;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}