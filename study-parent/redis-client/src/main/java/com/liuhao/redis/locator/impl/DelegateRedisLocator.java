package com.liuhao.redis.locator.impl;

import com.liuhao.redis.config.RedisLocation;
import com.liuhao.redis.locator.RedisLocator;

public class DelegateRedisLocator implements RedisLocator{

	private XmlRedisLocator xmlLocator = new XmlRedisLocator();

	private DBRedisLocator dbLocator = new DBRedisLocator();

	@Override
	public RedisLocation locator(String name) {

		RedisLocation redisLocation = xmlLocator.locator(name);

		if (redisLocation == null) {
			redisLocation = dbLocator.locator(name);
		}
		
		return redisLocation;
	}

}
