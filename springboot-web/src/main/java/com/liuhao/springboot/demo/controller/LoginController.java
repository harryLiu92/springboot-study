package com.liuhao.springboot.demo.controller;

import com.liuhao.springboot.demo.dto.UserDTO;
import com.liuhao.springboot.demo.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    private UserFacade userFacade;

    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Map<String, String> map,
                      HttpSession session) {

        logger.info("login username:{},password:{}", username, password);

        UserDTO user = userFacade.findByUserNameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

}
