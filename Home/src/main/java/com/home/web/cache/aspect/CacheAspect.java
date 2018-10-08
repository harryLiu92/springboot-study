package com.home.web.cache.aspect;

import com.google.common.collect.Maps;
import com.home.web.cache.annotation.CacheForHash;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 15:27
 * @Description:
 **/
@Aspect
@Component
public class CacheAspect {

    @Around("@annotation(com.home.web.cache.annotation.CacheForHash)")
    public Object cacheForHash(ProceedingJoinPoint jp) {
        Method currentMethod = ((MethodSignature)jp.getSignature()).getMethod();
        CacheForHash cache = (CacheForHash)currentMethod.getAnnotation(CacheForHash.class);
        Map<String, String> map = Maps.newHashMap();
        String hashKey = cache.hashKey();
        String param =  "\\{\\w\\}";



        try {
            return jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Around("@annotation(com.home.web.cache.annotation.CacheForValue)")
    public Object cacheForValue(ProceedingJoinPoint jp) {
        return null;
    }

}
