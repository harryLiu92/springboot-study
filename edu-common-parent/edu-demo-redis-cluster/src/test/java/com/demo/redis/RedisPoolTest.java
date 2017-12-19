/** 基于Dubbo的分布式系统架构视频教程，吴水成，wu-sc@foxmail.com，学习交流QQ群：367211134 **/
package com.demo.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Jedis连接池使用
 * @author liuhao 
 * @date   2017年12月1日下午4:35:39
 */
public class RedisPoolTest {
	private static final Log log = LogFactory.getLog(RedisPoolTest.class);

	public static void main(String[] args) {
		JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(100);  
        config.setMaxIdle(50);  
        config.setMinIdle(20);  
        config.setMaxWaitMillis(6 * 1000);  
        config.setTestOnBorrow(true);  
        
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        
        Jedis jedis = pool.getResource();
		String key = "wusc2";
		String value = jedis.get(key); // 取数据
		log.info(key + "=" + value);

	}
}
