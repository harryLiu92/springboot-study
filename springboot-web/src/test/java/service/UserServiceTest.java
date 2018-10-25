package service;

import com.liuhao.springboot.demo.SpringBootMain;
import com.liuhao.springboot.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 11:38
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMain.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByUserNameAndPassword() {
        System.out.println(userService.findByUserNameAndPassword("liuhao", "123456"));
    }

    @Test
    public void tt() {
        System.out.println(Integer.valueOf(1).equals(null));
    }
}
