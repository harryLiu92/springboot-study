package com.liuhao.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.liuhao.api.LoginService;
import com.liuhao.user.dao.BaseJdbcDao;

public class LoginServiceImpl implements LoginService{

    @Autowired
    private BaseJdbcDao basejdbcDao;

    @Override
    public String login(String username, String password) {
        basejdbcDao.insert("insert into dw_user(username, password) values('" + username + "', '" + password + "');");
        return "liuhao-basejdbcDao"+new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
//    
//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-dubbo.xml");
//        context.start();
//    }
}
