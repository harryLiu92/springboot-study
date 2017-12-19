package com.liuhao.redis.client;

import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuhao.redis.factory.RedisClientFactory;

import redis.clients.jedis.exceptions.JedisNoReachableClusterNodeException;

public class RedisClusterCallable {

	private static Logger logger = LoggerFactory.getLogger(RedisClusterCallable.class);

	private static final int TIMEOUT = 10000;

	// 重连
	private static final int CALL_FAIL = 6;

	public static <T> T callback(ICallable<T> callable) {
		return callback(callable, CALL_FAIL);
	}

	public static <T> T callback(ICallable<T> callable, int fail) {
		RedisClient proxy = RedisClientFactory.getClusterClient();

		T t = null;

		while (fail >= 0) {
			try {
				
				t = callable.call(proxy);
				
				break;
			} catch (UndeclaredThrowableException e) {
				fail--;
				
				proxy = RedisClientFactory.getClusterClient();
				
				logger.error("================>UndeclaredThrowableException fail = " + fail, e);
				
				try {
					Thread.sleep(TIMEOUT);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} catch (JedisNoReachableClusterNodeException e) {
				fail--;
				
				proxy = RedisClientFactory.getClusterClient();
				
				logger.error("================>JedisNoReachableClusterNodeException fail = " + fail, e);
				
				try {
					Thread.sleep(TIMEOUT);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		return t;
	}

	public interface ICallable<T> {
		T call(RedisClient proxy);
	}
}
