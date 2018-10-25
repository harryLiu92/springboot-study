package com.liuhao.springboot.demo.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

///**
// * Created by Song on 2017/2/15.
// * JPA 配置类
// */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Configuration
//@EnableTransactionManagement(proxyTargetClass = true)
//@EnableJpaRepositories(basePackages = "com.liuhao.springboot.demo.repository")
//@EntityScan(basePackages = "com.liuhao.springboot.demo.model")
//public class JpaConfiguration {
//    @Bean
//    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//}
