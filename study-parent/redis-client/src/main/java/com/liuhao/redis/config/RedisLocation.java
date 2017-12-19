package com.liuhao.redis.config;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;

import com.liuhao.common.utils.ReflectUtil;
import com.liuhao.redis.factory.AbstractRedisClientFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisLocation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RedisConfig redisConfig;

    private volatile JedisPool jedisPool;

    private volatile JedisCluster cluster;

	public boolean isCluster() {
    	if (StringUtils.equals(redisConfig.getName(), AbstractRedisClientFactory.__CLUSTER__))
			return true;
		return false;
    }

    public RedisLocation(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
        
        try {
			AbandonedConfig abandonedConfig = redisConfig.getAbandonedConfig();

			if (StringUtils.equals(AbstractRedisClientFactory.__CLUSTER__, redisConfig.getName())) {

				Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

				for (RedisNode node : redisConfig.getNode()) {
					jedisClusterNodes.add(new HostAndPort(node.getHost(), node.getPort()));
				}

		        cluster = new JedisCluster(jedisClusterNodes, redisConfig.getTimeout(), redisConfig.getMaxAttempts(), redisConfig.getJedisPoolConfig());  
			} else {
				String host = redisConfig.getNode().get(0).getHost();
				int port = redisConfig.getNode().get(0).getPort();
				
				jedisPool = new JedisPool(redisConfig.getJedisPoolConfig(), host, port, redisConfig.getTimeout());
			
				Field field = ReflectUtil.getTargetField(jedisPool.getClass(), "internalPool");

				Method method = ReflectUtil.getTargetMethod(GenericObjectPool.class, "setAbandonedConfig", new Class[] { AbandonedConfig.class });

				GenericObjectPool genericObjectPool = (GenericObjectPool) field.get(jedisPool);

				method.invoke(genericObjectPool, abandonedConfig);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public JedisPool getJedisPool() {
		return jedisPool;
	}

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public JedisCluster getCluster() {
		return cluster;
	}

	public void setCluster(JedisCluster cluster) {
		this.cluster = cluster;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
