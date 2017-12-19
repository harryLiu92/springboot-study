package com.liuhao.test;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Jedis命令
 *
 */
public class Jediscommand {

    private RedisClient proxy;

    @Before
    public void before() {
        proxy = RedisClientFactory.getClusterClient();
    }

    @Test
    public void testBasicCommand() {
//        proxy.del
    }
}
