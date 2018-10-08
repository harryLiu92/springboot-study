package com.home.web.builder;

import com.home.web.dto.LoginReponse;
import com.home.web.model.UserCustomer;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 17:55
 * @Description:
 **/
public class UserCustomerBuilder {

    public static UserCustomer builder(LoginReponse loginReponse) {
        UserCustomer userCustomer = new UserCustomer();
        BeanUtils.copyProperties(loginReponse, userCustomer);
        userCustomer.setCreateTime(new Date());
        userCustomer.setUpdateTime(new Date());

        return userCustomer;
    }
}
