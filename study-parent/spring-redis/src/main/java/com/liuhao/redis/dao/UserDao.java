package com.liuhao.redis.dao;

import com.liuhao.redis.entity.User;
import org.springframework.stereotype.Service;


public interface UserDao {

	public void saveUser(final User user);

	public User getUser(final long id);
}