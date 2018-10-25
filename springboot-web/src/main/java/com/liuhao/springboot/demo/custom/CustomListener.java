package com.liuhao.springboot.demo.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: liuhao
 * @Date: 2018/10/18 16:41
 * @Description:
 **/
public class CustomListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("--------------------------容器启动...-------------------------------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("--------------------------容器销毁...--------------------------------------");
    }
}
