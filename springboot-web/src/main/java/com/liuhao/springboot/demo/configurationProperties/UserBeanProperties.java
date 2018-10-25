package com.liuhao.springboot.demo.configurationProperties;

import java.io.Serializable;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 18:02
 * @Description:
 *
 **/
public class UserBeanProperties implements Serializable {
    private static final long serialVersionUID = 5149304938260044127L;
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
