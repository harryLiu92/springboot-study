package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.model.User;
import com.liuhao.springboot.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/10/15 22:47
 **/
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Map<String, String> map,
                      HttpSession session) {

        logger.info("login username:{},password:{}", username, password);

        User user = userService.findByUserNameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

}
