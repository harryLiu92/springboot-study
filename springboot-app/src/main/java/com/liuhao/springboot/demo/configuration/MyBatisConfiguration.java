package com.liuhao.springboot.demo.configuration;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 11:21
 * @Description:
 **/
@Configuration
public class MyBatisConfiguration {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {

            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);//设置驼峰命名规则
            }
        };
    }
}
