package com.liuhao.mybatis.dao;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.liuhao.mybatis.entity.User;
import com.liuhao.mybatis.entity.response.UserCompanyDto;

/**
 * @since  1.0.0
 * @author liuhao 
 * @date   2017年11月17日下午2:54:37
 * mybatis的测试类
 */
public class MybatisUserDaoTest {


	private SqlSessionFactory factory;

	private SqlSession session;

	private UserDao dao;

	@Before
	public void before() throws Exception {
		Reader reader = Resources.getResourceAsReader("Configuration.xml");

		factory = new SqlSessionFactoryBuilder().build(reader);

		session = factory.openSession();

		dao = session.getMapper(UserDao.class);

	}

	@After
	public void after() {
		session.commit();
		
		session.close();
	}

	@Test
	public void testSelectUserByID() {
		User user = new User();
		user.setId(4);
		user.setUserAge(28);
		RowBounds row = new RowBounds(1, 3);
		System.out.println(dao.selectUserByID(user, row));
	}

	@Test
	public void testselectUsersByName() {
		String userName = "summer";
		
		List<User> list =  dao.selectUsersByName(userName);
		
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
		List<Map<String, Object>> list =  dao.selectUserDynamicWhere(user);
		
		for (Map<String, Object> map  : list) {
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
		List<UserCompanyDto> dtos =  dao.selectUserResultMap(user);
		
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
		List<UserCompanyDto> dtos =  dao.selectUserParamResultMap(map);
		
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
		}
		System.out.println("user : " + user);
		dao.addUser(user);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		
		{
			user.setId(7);
			user.setUserAddress("hh");
			user.setUserAge(23);
			user.setUserName("llll");
		}
		
		System.out.println("user : " + user);
		
		dao.updateUser(user);
	}

	@Test
	public void testDeleteUser() {
		int id = 7;
		
		dao.deleteUser(id);
	}

}
