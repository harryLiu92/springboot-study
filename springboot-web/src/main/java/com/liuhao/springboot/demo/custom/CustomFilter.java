package com.liuhao.springboot.demo.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: liuhao
 * @Date: 2018/10/18 16:40
 * @Description:
 **/
public class CustomFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("MyFilter process...");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
