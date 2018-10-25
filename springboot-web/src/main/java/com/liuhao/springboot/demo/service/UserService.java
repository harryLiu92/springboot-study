package com.liuhao.springboot.demo.service;

import com.liuhao.springboot.demo.dto.modelQuery.UserQueryDTO;
import com.liuhao.springboot.demo.model.User;
import com.liuhao.springboot.demo.repository.UserBatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 11:16
 * @Description:
 **/
@Service
public class UserService {

//    @Autowired
//    private UserRepositoty userRepositoty;

    @Autowired
    private UserBatisMapper userBatisMapper;

    public User findByName(UserQueryDTO userQueryDTO) {
        if (userQueryDTO == null || "".equals(userQueryDTO.getName()) || userQueryDTO.getName() == null) {
            return null;
        }

//        return userRepositoty.findByName(userQueryDTO.getName());
        return new User();
    }

    public User findByUserNameAndPassword(String username, String password) {
        return userBatisMapper.findByUserNameAndPassword(username, password);
    }
}
