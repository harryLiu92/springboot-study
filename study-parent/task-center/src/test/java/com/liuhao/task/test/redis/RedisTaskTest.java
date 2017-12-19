package com.liuhao.task.test.redis;

import com.liuhao.spi.api.SPIBuilder;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.spi.core.SPILoader;
import com.liuhao.task.monitor.Monitor;
import com.liuhao.task.monitor.redis.RedisExpireSubscriber;
import com.liuhao.task.time.SequenceTimeRoll;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 17:54
 * @Description:
 **/
public class RedisTaskTest {

    public static void main(String[] args) {
        SPIBuilder.start();
//        Monitor monitor = SPILoader.getInstance(Monitor.class).getAdapteExtension();
        Monitor monitor = SPILoader.getInstance(Monitor.class).getExtension("redis");

        monitor.start(SPIRequest.name("redis"));
//        RedisExpireSubscriber.start();

        TimeTaskTest test = new TimeTaskTest(new SequenceTimeRoll("5"));

        monitor.publish(SPIRequest.name("redis"), test);
    }
}
