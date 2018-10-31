package com.liuhao.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: liuhao
 * @Date: 2018/10/31 11:14
 * @Description:
 **/
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootJettyMain {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJettyMain.class);
    }
}
