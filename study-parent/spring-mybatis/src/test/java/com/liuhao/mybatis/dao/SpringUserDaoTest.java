package com.liuhao.mybatis.dao;

import com.liuhao.common.utils.BaseTest;
import com.liuhao.mybatis.entity.Page;
import com.liuhao.mybatis.entity.User;
import com.liuhao.mybatis.entity.response.UserCompanyDto;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration("classpath:spring-context.xml")
public class SpringUserDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;


    /**
     * @author liuhao
     * @date 2017年11月17日下午2:55:50
     * 分页,分表方法
     * @since 1.0.0
     */
    @Test
    public void testSelectUserByID() {
        User user = new User();
        user.setUserId(99);
        user.setUserAge(23);
        RowBounds rowBounds = new RowBounds(0, 3);
        List<User> users = userDao.selectUserByID(user, rowBounds);
        Page<User> page = new Page(users);
        System.out.println("page : " + page);

        for (User u : page.getData()) {
            System.out.println(u);
        }
    }


    /**
     * @author liuhao
     * @date 2017年11月17日下午2:54:17
     * 普通测试类
     * @since 1.0.0
     */
    @Test
    public void testSelectUserByUserid() {
        User user = new User();
        user.setId(4);
        user.setUserId(11);
        user.setUserAge(30);
        List<User> users = userDao.selectUserByUserId(user);
        System.out.println("users : " + users.size());
        for (User u : users) {
            System.out.print(u);
        }
    }


    @Test
    public void testselectUsersByName() {
        String userName = "summer";

        List<User> list = userDao.selectUsersByName(userName);

        System.out.println(list);
    }

    @Test
    public void testSelectUserDynamicWhere() {
        User user = new User();
        {
            user.setUserAddress("some place");
            user.setUserAge(29);
            user.setUserName("t");
        }
        List<Map<String, Object>> list = userDao.selectUserDynamicWhere(user);

        for (Map<String, Object> map : list) {
            int userAge = (Integer) map.get("userAge");
            System.out.println(map);
            System.out.println(map.get("userAge"));
            System.out.println("userAge : " + userAge);

        }
    }

    @Test
    public void testSelectUserResultMap() {
        User user = new User();
        {
            user.setUserAddress("some place");
            user.setUserAge(29);
            user.setUserName("t");
        }
        List<UserCompanyDto> dtos = userDao.selectUserResultMap(user);

        for (UserCompanyDto dto : dtos) {
            System.out.println(dto);
        }
    }

    @Test
    public void testSelectUserParamResultMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        {
            map.put("userName", "t");
            map.put("userAge", 29);
            map.put("userAddress", "some place");
            map.put("companyAddress", "sz");
        }
        List<UserCompanyDto> dtos = userDao.selectUserParamResultMap(map);

        for (UserCompanyDto dto : dtos) {
            System.out.println(dto);
        }
    }

    @Test
    public void testAddUser() {

        User user = new User();

        {
            user.setUserAddress("sz");
            user.setUserAge(22);
            user.setUserName("lh");
            user.setUserId(55);
            user.setCompany(4);
        }
        System.out.println("user : " + user);
        userDao.addUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();

        {
            user.setUserId(99);
            user.setUserAddress("hh");
            user.setUserAge(23);
            user.setUserName("llll");
        }

        System.out.println("user : " + user);

        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        int userId = 99;

        userDao.deleteUser(userId);
    }

    public static void main(String[] args) {
//		String value = "15";
//		int hashCode = Math.abs(value.hashCode());
//
//		int index = (int) (hashCode % 4);
//
//		System.out.println(index);
        String sql = "  SELECT SQL_CALC_FOUND_ROWS ID, USERID, USERNAME, USERAGE, USERADDRESS, COMPANY FROM T_USER_3 WHERE USERID = ? AND USERAGE = ? LIMIT 0, 3 ";
        int index = sql.indexOf("SELECT");
        System.out.println(index);
        sql = sql.trim();
        System.out.println(sql.substring("SELECT".length(), sql.length()));
        System.out.println();
    }
}
