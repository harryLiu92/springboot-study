package com.home.web.controller;

import com.home.web.constant.URL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Administrator liuhao
 * 2018/9/11 1:48
 **/
@Controller
@RequestMapping(URL.CUSTOMER_ROOT)
public class CustomerController {

    @RequestMapping(URL.CUSTOMER_INDEX)
    public void customerIndex() {

    }
}