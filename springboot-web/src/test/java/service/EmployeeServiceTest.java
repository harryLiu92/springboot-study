package service;

import com.liuhao.springboot.demo.SpringBootMain;
import com.liuhao.springboot.demo.model.Employee;
import com.liuhao.springboot.demo.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/10/17 17:13
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMain.class)
public class EmployeeServiceTest {

    @Autowired
    private EmpService empService;

    @Test
    public void updateBYId() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("LIUHAO");
        employee.setEmail("bb@166.com");
        employee.setGender(0);
        employee.setDepartmentid(3);
        employee.setBirth(new Date());
        System.out.println(empService.updateById(employee));
    }
}
