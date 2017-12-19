package com.liuhao.redis.factory;

import com.liuhao.redis.client.RedisClient;

public class RedisClientFactory extends AbstractRedisClientFactory {

	public static RedisClient getSingleClient() {
		return getClient("single");
	}

	public static RedisClient getClusterClient() {
		return getClient(__CLUSTER__);
	}
}
