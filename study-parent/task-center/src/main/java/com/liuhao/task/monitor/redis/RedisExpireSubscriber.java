package com.liuhao.task.monitor.redis;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.spi.core.SPILoader;
import com.liuhao.task.entity.TimeTaskConfig;
import com.liuhao.task.monitor.Monitor;
import com.liuhao.task.monitor.impl.RedisMonitor;
import com.liuhao.task.provider.TimeTaskConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisExpireSubscriber extends JedisPubSub {

    private static final Logger logger = LoggerFactory.getLogger(RedisMonitor.class);

    public static final String EXPIRE_CHANNEL = "__keyevent@0__:expired";

    public static void start() {

        Callable<Void> callable = new Callable<Void>() {

            @Override
            public Void call() {
                logger.info("----------------SubscribeThread.start------------------");

                RedisClient client = RedisClientFactory.getSingleClient();

                RedisExpireSubscriber sub = new RedisExpireSubscriber();

                client.subscribe(sub, EXPIRE_CHANNEL);

                logger.info("----------------SubscribeThread.end------------------");

                return null;
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.submit(callable);
    }

    public void onMessage(String channel, String message) {
        try {

            logger.info("----------RedisExpireSubscriber.onMessage.start = {}-----------------", message);

            TimeTaskConfig config = TimeTaskConfigProvider.parseConfig(message);

            logger.info("----------RedisExpireSubscriber.onMessage.end1 = {}-----------------", config);

//            SPILoader.getInstance(Monitor.class).getAdapteExtension().acquire(SPIRequest.name("redis"), config);
            RedisMonitor monitor = new RedisMonitor();
            monitor.acquire(SPIRequest.name("redis"), config);

            logger.info("----------RedisExpireSubscriber.onMessage.end = {}-----------------", config);
        } catch (Exception e) {
            logger.error(String.format("----------RedisExpireSubscriber.onMessage.ERROR config = {%1$s}-----------------", message), e);
        }
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe : " + channel);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe : " + channel);
    }

}
