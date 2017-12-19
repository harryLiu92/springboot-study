package com.liuhao.task.provider;

import com.liuhao.task.time.AppointTimeRoll;
import com.liuhao.task.time.SequenceTimeRoll;
import com.liuhao.task.time.TimeRoll;
import com.liuhao.task.time.TimeRollEnum;

import java.util.HashMap;

/**
 * @Author: liuhao
 * @Date: 2017/12/15 14:03
 * @Description:
 **/
public class TimeRollProvider {

    private static final HashMap<Integer, TimeRoll> timeRollMap = new HashMap<Integer, TimeRoll>() {
        {
            put(TimeRollEnum.SEQUENCE.getType(), new SequenceTimeRoll("-1"));
//            put(TimeRollEnum.APPOINT.getType(), new AppointTimeRoll(-1));
        }
    };

    public static TimeRoll parseTimeRoll(String second, int type) {
        TimeRoll timeRoll = null;
        if (timeRollMap.containsKey(type)) {
            timeRoll = timeRollMap.get(type);
            timeRoll.setTime(second);
        }
        return timeRoll;
    }
}
