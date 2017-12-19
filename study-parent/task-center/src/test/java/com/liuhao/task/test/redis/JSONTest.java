package com.liuhao.task.test.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhao.task.entity.TimeTaskConfig;
import com.liuhao.task.provider.TimeTaskConfigProvider;
import com.liuhao.task.time.SequenceTimeRoll;

/**
 * @Author: liuhao
 * @Date: 2017/12/15 15:16
 * @Description:
 **/
public class JSONTest {
    public static void main(String[] args) {
////        TimeTaskTest test = new TimeTaskTest();
//        SequenceTimeRoll timeRoll = new SequenceTimeRoll("5");
////        test.setTimeRoll(timeRoll);
//        String timeRolljson = JSON.toJSONString(timeRoll);
//        System.out.println("json " + timeRolljson);
//        System.out.println("timeRoll " + JSONObject.parseObject(timeRolljson, SequenceTimeRoll.class));

        String json = "{\"attr\":\"{\"timeRoll\":{\"time\":\"5\",\"timeRollType\":2}}\",\"clazz\":\"com.liuhao.task.test.redis.TimeTaskTest\"}";

        TimeTaskConfig config = TimeTaskConfigProvider.parseConfig(json);

        System.out.println(config);
    }
}
