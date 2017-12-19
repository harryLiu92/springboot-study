package com.liuhao.redis.locator.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.liuhao.common.utils.FileUtil;
import com.liuhao.common.utils.XmlUtil;
import com.liuhao.redis.config.RedisConfig;
import com.liuhao.redis.config.RedisList;
import com.liuhao.redis.config.RedisLocation;
import com.liuhao.redis.locator.RedisLocator;

public class XmlRedisLocator implements RedisLocator{

	private final static String XML_PATH = "redis.xml";

	private final static ConcurrentHashMap<String, RedisConfig> locations = new ConcurrentHashMap<String, RedisConfig>();

	@Override
	public RedisLocation locator(String name) {

		if (locations.isEmpty()) {
			String txt = FileUtil.read(XML_PATH);
			RedisList redisList = XmlUtil.parseToJavaBean(txt, RedisList.class);

			if (redisList == null) {
				throw new RuntimeException(XML_PATH + " Path loaded xml is null ");
			}

			for (RedisConfig redis : redisList.getRedis()) {

				locations.put(redis.getName(), redis);
			}
		}

		RedisConfig redis = locations.get(name);

		RedisLocation redisLocation = new RedisLocation(redis);

		return redisLocation;
	}

}
