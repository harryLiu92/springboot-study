package com.home.web.aspect;

import com.home.web.annonation.LogName;
import com.home.web.logger.BaseService;
import com.home.web.utils.IdentityIdHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: liuhao
 * @Date: 2018/6/7 11:13
 * @Description:
 **/
@Aspect
@Component
public class LogNameAspect extends BaseService {

    @Pointcut("@annotation(com.home.web.annonation.LogName)")
    public void insertLogName() {}

    @Around("insertLogName()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        Object[] params = pjp.getArgs();

        long beginTime = System.currentTimeMillis();
        String methodName = null;
        int index = 0;
        LogName logName = null;
        try {
            if (params != null && params.length > 0) {
                MethodSignature signature = (MethodSignature) pjp.getSignature();
                Method method = signature.getMethod();
                methodName = method.getName();
                logName = method.getAnnotation(LogName.class);

                String value = logName.value();
                index = IdentityIdHolder.appendLog(value);
            }
        } catch (Throwable t) {
            logger.error("注入日志方法调用异常，error-{}", t);
        }

        try {
            return pjp.proceed();
        } finally {
            methodName =  methodName == null ? "aopLog" : methodName;
            long endTime = System.currentTimeMillis();
            logger.info("invoke " +methodName + " cost "+ (endTime - beginTime) +" ms");
        }
    }
}
