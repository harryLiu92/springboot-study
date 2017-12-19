package com.liuhao.redis.locator;

import com.liuhao.redis.config.RedisLocation;

public interface RedisLocator {

	RedisLocation locator(String name);
}
