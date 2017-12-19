package com.liuhao.redis.factory;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.client.RedisClientProxy;
import com.liuhao.redis.resource.RedisResourceFactory;
import com.liuhao.redis.resource.ResourcesManager;

public class AbstractRedisClientFactory {

	public static final String __CLUSTER__ = "__cluster__";

	private static boolean start = false;

	private static ResourcesManager<RedisClient> resourcesManager;

	public static void start() {
		if (!start) {
			start = true;

			ResourcesManager.ResourceFactory<RedisClient> redisResourceFactory = new RedisResourceFactory();

			resourcesManager = new ResourcesManager<RedisClient>(redisResourceFactory);
		}
	}

	public static boolean isStart() {
		return start;
	}

	public static RedisClient getClient(String name) {
		if (!isStart()) {
			start();
		}

		return resourcesManager == null ? null : resourcesManager.get(name);
	}

	public static void close() {
		resourcesManager.close();
	}
}
