package com.liuhao.redis.resource;

import com.liuhao.redis.config.RedisLocation;
import com.liuhao.redis.locator.impl.DelegateRedisLocator;

public class RedisResourcesManager {

	public static RedisLocation getRedisLocation(String name) {
		DelegateRedisLocator delegate = new DelegateRedisLocator();

		return delegate.locator(name);
	}
}
