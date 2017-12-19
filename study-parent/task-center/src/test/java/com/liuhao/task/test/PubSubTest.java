package com.liuhao.task.test;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import com.liuhao.task.monitor.redis.RedisExpireSubscriber;
import org.junit.Test;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.*;

public class PubSubTest {


    /**
     * 自定义订阅/发布
     */
    public static void customPubSub() {
        RedisClient redisClient = RedisClientFactory.getSingleClient();

        Subscriber subscriber = new Subscriber("mychannel");
        Publisher publisher = new Publisher();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(subscriber);

        executor.submit(publisher);
    }

    /**
     * 过期键订阅/发布
     */
    public static void expiredKey() {
        RedisClient redisClient = RedisClientFactory.getSingleClient();

        Subscriber thread = new Subscriber(RedisExpireSubscriber.EXPIRE_CHANNEL);

        ExecutorService executor = Executors.newFixedThreadPool(1);

        executor.submit(thread);
    }

    public static void main(String[] args) {
//        customPubSub();

//        expiredKey();

        RedisExpireSubscriber.start();
    }



}
