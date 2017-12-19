package com.liuhao.redis.resource;

import java.lang.reflect.Proxy;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.client.RedisClientProxy;
import com.liuhao.redis.client.RedisClusterClientProxy;
import com.liuhao.redis.config.RedisLocation;

public class RedisResourceFactory implements ResourcesManager.ResourceFactory<RedisClient>{

	@Override
	public RedisClient getResource(String name) {

		RedisLocation location = RedisResourcesManager.getRedisLocation(name);

		RedisClient redisClient = null;

		if (location.isCluster()) {

			// 动态代理
			redisClient = (RedisClient)Proxy.newProxyInstance(
					RedisClient.class.getClassLoader(), 
					new Class[] {RedisClient.class}, 
					new RedisClusterClientProxy(location));

		} else {

			// 动态代理
			redisClient = (RedisClient)Proxy.newProxyInstance(
					RedisClient.class.getClassLoader(), 
					new Class[] {RedisClient.class}, 
					new RedisClientProxy(location));
		}

		return redisClient;
	}

	@Override
	public void close(String name, RedisClient client) {
		client.close();
	}

}
