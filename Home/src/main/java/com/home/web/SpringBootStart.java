package com.home.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:30
 * @Description:
 * 项目启动入口，配置包根路径
 **/
//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.home.web")
@MapperScan(basePackages= {"com.home.web.dao"})
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class, args);
    }
}