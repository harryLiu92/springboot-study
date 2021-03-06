package com.liuhao.springboot.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.liuhao.springboot.demo.dto.BaseObject;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:08
 * @Description:
 **/
public class User extends BaseObject {

    private static final long serialVersionUID = 2202278013931870229L;
    private long id;

    private String name;

    private String password;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
