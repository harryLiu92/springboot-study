package com.liuhao.redis.dao.impl;

import com.liuhao.redis.dao.UserDao;
import com.liuhao.redis.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends BaseTest{

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setId(1);
		user.setName("liuhao");
		userDao.saveUser(user);
	}

	@Test
	public void testGetUser() {
		System.out.println(userDao.getUser(1L));
	}

}
