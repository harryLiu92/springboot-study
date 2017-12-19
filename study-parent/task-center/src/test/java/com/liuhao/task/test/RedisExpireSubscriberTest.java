package com.liuhao.task.test;

import com.liuhao.spi.api.SPIRequest;
import com.liuhao.spi.core.SPILoader;
import com.liuhao.task.monitor.Monitor;
import redis.clients.jedis.JedisPubSub;

public class RedisExpireSubscriberTest extends JedisPubSub {

    public static final String EXPIRE_CHANNEL = "__keyevent@0__:expired";

    public void onMessage(String channel, String message) {

        System.out.println("onMessage : " + message);

//        SPILoader.getInstance(Monitor.class).getAdapteExtension().acquire(SPIRequest.name("redis"), message);

    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("onSubscribe : " + channel);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("onUnsubscribe : " + channel);
    }

}
