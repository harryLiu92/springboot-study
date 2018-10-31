package com.liuhao.study.config;

import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuhao
 * @Date: 2018/10/31 13:58
 * @Description:
 **/
@Configuration
public class JettyConfiguration {

    @Bean
    public JettyEmbeddedServletContainerFactory jettyEmbeddedServletContainerFactory() {
        return new JettyEmbeddedServletContainerFactory();
    }
}