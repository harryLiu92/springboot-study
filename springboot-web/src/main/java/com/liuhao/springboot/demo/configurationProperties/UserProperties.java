package com.liuhao.springboot.demo.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 16:02
 * @Description:
 * 一,ConfigurationProperties加载文件到实体,必须是spring管理的实体
 * prefix：过滤哪种前缀数据
 * PropertySource指定资源文件
 * 如果不指定，默认从application.properties或者application.yml读取
 **/
@Component
@ConfigurationProperties(prefix = "user")
//@PropertySource("user.properties")
public class UserProperties implements Serializable{
    private static final long serialVersionUID = 7856485897463824254L;
    private long id;

    private String userName;

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
