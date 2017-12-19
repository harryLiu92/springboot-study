package com.liuhao.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.liuhao.mybatis.entity.User;
import com.liuhao.mybatis.entity.response.UserCompanyDto;

@Repository
public interface UserDao {

	public List<User> selectUserByUserId(User user);

	public List<User> selectUserByID(User user, RowBounds rowBounds);

	public List<User> selectUsersByName(String userName);

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int userId);
	
	public List<Map<String, Object>> selectUserDynamicWhere(User user);
	
	public List<UserCompanyDto> selectUserResultMap(User user);
	
	public List<UserCompanyDto> selectUserParamResultMap(Map<String, Object> map);

}
