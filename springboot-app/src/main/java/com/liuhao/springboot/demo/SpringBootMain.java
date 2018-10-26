package com.liuhao.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:30
 * @Description:
 * 项目启动入口，配置包根路径
 **/
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.liuhao.springboot.demo")
@MapperScan("com.liuhao.springboot.demo.repository")
public class SpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
//
//        try {
//            Thread.sleep(10000000);
//        } catch (Throwable ex) {}
    }
}