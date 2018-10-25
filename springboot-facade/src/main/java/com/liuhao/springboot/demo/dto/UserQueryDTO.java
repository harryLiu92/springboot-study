package com.liuhao.springboot.demo.dto.modelQuery;

import java.io.Serializable;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:18
 * @Description:
 **/
public class UserQueryDTO implements Serializable {
    private static final long serialVersionUID = 7351642746120551944L;
    private long id;

    private String name;

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
