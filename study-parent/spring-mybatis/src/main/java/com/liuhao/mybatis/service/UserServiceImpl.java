package com.liuhao.mybatis.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuhao.mybatis.dao.UserDao;
import com.liuhao.mybatis.entity.User;
import com.liuhao.mybatis.entity.response.UserCompanyDto;
import com.liuhao.mybatis.service.api.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<User> selectUserByID(User user, RowBounds rowBounds) {
		return userDao.selectUserByID(user, rowBounds);
	}

	public List<User> selectUsersByName(String userName) {
		return userDao.selectUsersByName(userName);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public List<Map<String, Object>> selectUserDynamicWhere(User user) {
		return userDao.selectUserDynamicWhere(user);
	}

	public List<UserCompanyDto> selectUserResultMap(User user) {
		return userDao.selectUserResultMap(user);
	}

	public List<UserCompanyDto> selectUserParamResultMap(Map<String, Object> map) {
		return userDao.selectUserParamResultMap(map);
	}

}
