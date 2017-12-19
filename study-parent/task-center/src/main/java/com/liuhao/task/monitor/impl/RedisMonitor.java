package com.liuhao.task.monitor.impl;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.task.monitor.AbstractMonitor;
import com.liuhao.task.monitor.redis.RedisExpireSubscriber;
import com.liuhao.task.provider.TimeTaskProvider;
import com.liuhao.task.time.TimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 16:15
 * @Description:
 **/
public class RedisMonitor extends AbstractMonitor {

    private static final Logger logger = LoggerFactory.getLogger(RedisMonitor.class);

    @Override
    public void start(SPIRequest req) {
        RedisExpireSubscriber.start();
    }

    @Override
    public void publish(SPIRequest req, TimeTask task) {

        logger.info("-----------redis.publish.start task = {}----------",
                new Object[] { task});
        RedisClient client = RedisClientFactory.getSingleClient();

        String key = TimeTaskProvider.parseKey(task);
        int expired = TimeTaskProvider.parseExpired(task);

        client.setex(key, expired,"");

        client.close();

        logger.info("-----------redis.publish.success task = {}, key = {}, expired = {}----------",
                new Object[] { task, key, expired });

    }

}
