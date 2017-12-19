package com.liuhao.task.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liuhao.task.time.SequenceTimeRoll;
import com.liuhao.task.time.TimeTask;
import com.liuhao.task.entity.TimeTaskConfig;

import java.lang.reflect.Field;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 16:08
 * @Description:
 **/
public class TimeTaskProvider {

    public static TimeTask parseTimeTask(TimeTaskConfig config) {

        String clazz = config.getClazz();

        Class<? extends TimeTask> clas = null;
        try {
            clas = (Class<? extends TimeTask>) Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//
//
//        SequenceTimeRoll timeRoll = new SequenceTimeRoll("5");
//        String j = JSON.toJSONString(timeRoll);
//        JSONObject jobj = JSONObject.parseObject(j);
//        String second = (String) jobj.get("second");
//        Integer timeRollType = (Integer) jobj.get("timeRollType");

//        System.out.println("timeRoll : " + TimeRollProvider.parseTimeRoll(second, timeRollType));
        return JSONObject.parseObject(config.getAttr(), clas);
    }

    /**
     * @Author: liuhao
     * @Date: 2017/12/15 10:48
     * @param task
     * @Description:
     * task -> config -> json
     */
    public static String parseKey(TimeTask task) {

        String attr = parseTimeRollJSON(task);

        TimeTaskConfig config = new TimeTaskConfig(task.getClass().getName(), attr);

        return JSONObject.toJSONString(config);
    }

    public static int parseExpired(TimeTask task) {
        int expired = 0;

        if (task != null && task.getTimeRoll() != null) {
            expired = task.getTimeRoll().next();
        }

        return expired;
    }

    private static String parseTimeRollJSON(TimeTask task) {
        if (task == null)
            task = new TimeTask() {
                @Override
                public Object execute() {
                    return null;
                }
            };

        return JSONObject.toJSONString(task);
    }

}