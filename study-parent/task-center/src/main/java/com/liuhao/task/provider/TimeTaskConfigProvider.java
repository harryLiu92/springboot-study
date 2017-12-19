package com.liuhao.task.provider;

import com.alibaba.fastjson.JSONObject;
import com.liuhao.task.entity.TimeTaskConfig;
import com.liuhao.task.time.TimeTask;

/**
 * @Author: liuhao
 * @Date: 2017/12/15 10:24
 * @Description:
 **/
public class TimeTaskConfigProvider {


    public static TimeTaskConfig parseConfig(String message) {
        return JSONObject.parseObject(message, TimeTaskConfig.class);
    }
}
