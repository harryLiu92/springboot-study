package com.liuhao.springboot.demo.configuration;

import com.liuhao.springboot.demo.custom.CustomFilter;
import com.liuhao.springboot.demo.custom.CustomListener;
import com.liuhao.springboot.demo.custom.CustomServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @Author: liuhao
 * @Date: 2018/10/18 16:29
 * @Description:
 **/
@Configuration
public class ServletConfiguration {

    /**
     * 内嵌Servlet容器,修改web.xml配置参数Servlet容器相关的规则
     */
    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
        EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer = new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(9090);
            }
        };

        return embeddedServletContainerCustomizer;
    }

    @Bean
    public ServletRegistrationBean customServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CustomServlet(),"/customServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean customFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CustomFilter());
        filterRegistrationBean.setFilter(new CustomFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/customServlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean customListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean(new CustomListener());
        return servletListenerRegistrationBean;
    }
}