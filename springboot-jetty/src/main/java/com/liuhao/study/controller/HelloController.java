package com.liuhao.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuhao
 * @Date: 2018/10/31 11:17
 * @Description:
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello" + name;
    }
}
