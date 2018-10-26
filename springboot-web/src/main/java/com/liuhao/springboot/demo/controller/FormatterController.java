package com.liuhao.springboot.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.liuhao.springboot.demo.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 10:44
 * @Description:
 **/
@RestController
public class FormatterController {

    @ResponseBody
    @RequestMapping(value = "/formatDate")
    public String formatDate(@RequestParam("date") Date date) {
        return "is ok!" + date;
    }

    @ResponseBody
    @RequestMapping(value = "/parseDate")
    public String parseDate() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("liuhao");
        user.setPassword("123456");
        user.setCreateTime(new Date());
        return JSONObject.toJSONString(user);
    }

}
