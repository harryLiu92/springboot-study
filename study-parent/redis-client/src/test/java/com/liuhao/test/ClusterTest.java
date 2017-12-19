package com.liuhao.test;

import java.lang.reflect.UndeclaredThrowableException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.client.RedisClusterCallable;
import com.liuhao.redis.client.RedisClusterCallable.ICallable;
import com.liuhao.redis.factory.RedisClientFactory;

public class ClusterTest {

	Logger logger = LoggerFactory.getLogger(ClusterTest.class);

	@Test
	public void fallover() {

		try {

			try {
				RedisClusterCallable.callback(new ICallable<Void>() {

					@Override
					public Void call(RedisClient proxy) {
						boolean flag = true;
						while (flag) {
							logger.info("====>start===========================");
							int num = 1000;
							for (int i = 1; i <= num; i++) {
								String key = "hello";
								String value = "";
								// if (i == 10)
								// RedisClientFactory.close();
								// 存数据
								// proxy.set(key+i, "hello"+i);

								// 取数据
								value = proxy.get(key + i);
								// logger.info(key + i + "=" + value);
							}
							logger.info("====>end===========================");
						}
						return null;
					}

				});
			} catch (Exception e) {
				logger.error("====> exception1 ", e);
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			logger.error("====> exception2 ", e);
		}

	}

	@Test
	public void fallover2() {

		RedisClient proxy = RedisClientFactory.getClusterClient();
		int num = 1000;
		String key = "hello";
		String value = "";
		int count = 1;

		try {

			while (true) {
				logger.info("====>start===========================");
				for (int i = 1; i <= num; i++) {

					try {

						// 存数据
						// proxy.set(key+i, "hello"+i);
						// 取数据
						value = proxy.get(key + i);

						logger.info(key + i + "=" + value);
						if (value == null || "".equals(value)) {

							logger.error("===>break" + key + i + " value is null");

							break;

						}

					} catch (Exception e) {

						logger.error("====>", e);
						Thread.sleep(1000);
						proxy = RedisClientFactory.getClusterClient();

						continue;
					}

					// 删除数据x
					// jedisCluster.del(key+i);
					// value = jedisCluster.get(key+i);
					// log.info(key+i + "=" + value);

				}

				if (value == null || "".equals(value)) {
					break;
				}

				count++;
				logger.info("====>end===========================");
			}

		} catch (Exception e) {
			logger.error("====>", e);
		}

	}

	@Test
	public void save() {
		RedisClient proxy = RedisClientFactory.getClusterClient();

		int num = 1000;
		String key = "hello";
		String value = "";
		for (int i = 1; i <= num; i++) {
			// 存数据
			proxy.set(key + i, "world" + i);
			// 取数据
			value = proxy.get(key + i);
			System.out.println(key + i + "=" + value);
			// 删除数据
			// jedisCluster.del(key+i);
			// value = jedisCluster.get(key+i);
			// log.info(key+i + "=" + value);
		}
	}

}
