package com.home.web.test.service;

import com.home.web.model.UserCustomer;
import com.home.web.service.UserCustomerService;
import com.home.web.test.BaseSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 10:53
 * @Description:
 **/
public class LoginServiceTest extends BaseSpringBootTest {

    @Autowired
    private UserCustomerService userCustomerService;

    @Test
    public void insert() {
        UserCustomer userCustomer = new UserCustomer();
        userCustomer.setOpenid("openid");
        userCustomer.setNickName("文刀三告");
        userCustomer.setGender(1);
        userCustomer.setAvatarUrl("avatarUrl");
        userCustomer.setProvince("guangdong");
        userCustomer.setCity("shenzhen");
        userCustomer.setCreateTime(new Date());
        userCustomer.setUpdateTime(new Date());
        userCustomerService.insert(userCustomer);
    }
}
