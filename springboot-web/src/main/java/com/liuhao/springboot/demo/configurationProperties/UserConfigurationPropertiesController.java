package com.liuhao.springboot.demo.configurationProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 17:59
 * @Description:
 *二,ConfigurationProperties可以使用在Bean注解上,那么实体类上的@Component和@ConfigurationProperties都不需要
 **/
@Controller
@RequestMapping("/userConfigurationProperties")
public class UserConfigurationPropertiesController {
    @Autowired
    private UserProperties userProperties;
    @Autowired
    private UserBeanProperties userBeanProperties;

    @ConfigurationProperties(prefix = "user")
    @Bean
    public UserBeanProperties getUserBeanProperties() {
        return new UserBeanProperties();
    }

    @RequestMapping(value = "/showProperties")
    @ResponseBody
    public String showProperties(){
        return userProperties.getId()+"/"+userProperties.getUserName()+"/"+userProperties.getPassword();
    }

    @RequestMapping(value = "/showBeanProperties")
    @ResponseBody
    public String showBeanProperties(){
        return userBeanProperties.getId()+"/"+userBeanProperties.getUserName()+"/"+userBeanProperties.getPassword();
    }
}
