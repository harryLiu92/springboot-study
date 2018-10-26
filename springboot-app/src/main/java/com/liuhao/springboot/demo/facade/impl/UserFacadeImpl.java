package com.liuhao.springboot.demo.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.liuhao.springboot.demo.dto.UserDTO;
import com.liuhao.springboot.demo.facade.UserFacade;
import com.liuhao.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: liuhao
 * @Date: 2018/10/25 17:34
 * @Description:
 **/
@Service(version="1.0.0")
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO findByUserNameAndPassword(String username, String password) {
        return userService.findByUserNameAndPassword(username, password);
    }
}
