package com.home.web.cache.annotation;

import com.home.web.cache.constant.CacheHashOperationType;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 16:07
 * @Description:
 *     @CacheForHash(operation = CacheHashOperationType.PUT, key = "login", hashKey = "id_{openid}", hashValue = "{userCustomer}")
 **/
public @interface CacheForHash {

    CacheHashOperationType operation();

    String key() default "";

    String hashKey() default "";

    String hashValue() default "";

    long timeout() default 0L;

    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
