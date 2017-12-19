package com.liuhao.task.test;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Callable;

public class Subscriber implements Callable {
//public class Subscriber implements Runnable {

    private RedisExpireSubscriberTest subscriber = new RedisExpireSubscriberTest();

    private String channel;

    public Subscriber(String channel) {
        this.channel = channel;
    }

    @Override
    public Void call() {
//    public void run() {
        System.out.println("----------------SubscribeThread.start------------------");

        RedisClient client = RedisClientFactory.getSingleClient();

        RedisExpireSubscriberTest sub = new RedisExpireSubscriberTest();

        client.subscribe(sub, channel);

        System.out.println("----------------SubscribeThread.end------------------");

        return null;
    }

}
