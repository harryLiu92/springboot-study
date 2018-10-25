package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.configurationProperties.UserProperties;
import com.liuhao.springboot.demo.dto.modelQuery.UserQueryDTO;
import com.liuhao.springboot.demo.model.User;
import com.liuhao.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:22
 * @Description:
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "user/index";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        userQueryDTO.setName(name);
        User user = userService.findByName(userQueryDTO);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "null";
    }
}
