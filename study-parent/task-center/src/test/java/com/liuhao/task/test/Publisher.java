package com.liuhao.task.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import com.liuhao.redis.client.RedisClient;
import com.liuhao.redis.factory.RedisClientFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher implements Callable{
//public class Publisher implements Runnable{

    public Void call() {
//    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RedisClient client = RedisClientFactory.getSingleClient();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    client.publish("mychannel", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}