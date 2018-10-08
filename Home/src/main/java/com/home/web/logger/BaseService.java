package com.home.web.logger;


import org.slf4j.LoggerFactory;

/**
 * @Author: liuhao
 * @Date: 2018/4/17 11:34
 * @Description:
 **/
public abstract class BaseService {

    protected final transient Logger logger = new InterLoggerFactory(LoggerFactory.getLogger(this.getClass()));

    public BaseService() {
    }

}
