package com.home.web.test.service;

import com.home.web.service.UserCustomerService;
import com.home.web.test.BaseSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 16:59
 * @Description:
 **/
public class CacheTest extends BaseSpringBootTest{

    @Autowired
    private UserCustomerService userCustomerService;

    @Test
    public void cash() {
        userCustomerService.login(null);
    }

}
