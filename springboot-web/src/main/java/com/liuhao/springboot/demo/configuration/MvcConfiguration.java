package com.liuhao.springboot.demo.configuration;

import com.liuhao.springboot.demo.custom.CustomLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/10/16 10:46
 * @Description:
 * 可以使用@Bean WebMvcConfigurerAdapter可以使用重写方法
 **/
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private WebMvcProperties mvcProperties;

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            // 全局的视图配置
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                //静态资源；  *.css , *.js
//                //SpringBoot已经做好了静态资源映射
//                registry.addInterceptor(new LoginHandleInterceptor())
//                        .addPathPatterns("/**")
//                        .excludePathPatterns("/","/index.html","/login");
//            }
        };
        return adapter;
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.mvc", name = "date‐format")
    public Formatter<Date> formatter() {
        return new DateFormatter(this.mvcProperties.getDateFormat());
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }
}
