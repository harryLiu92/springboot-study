package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:22
 * @Description:
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserFacade userFacade;

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

//    @RequestMapping(value = "/show")
//    @ResponseBody
//    public String show(@RequestParam(value = "name")String name){
//        userFacade.
//        if(null != user)
//            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
//        else return "null";
//    }
}
