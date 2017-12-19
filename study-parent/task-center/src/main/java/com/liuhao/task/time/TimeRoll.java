package com.liuhao.task.time;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Author: liuhao
 * @Date: 2017/12/9 17:33
 * @Description:
 **/
public abstract class TimeRoll {

    public TimeRoll(String time) {
        this.time = time;
    }

    public abstract int next();

    private String time;

    private int timeRollType;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTimeRollType() {
        return timeRollType;
    }

    public void setTimeRollType(int timeRollType) {
        this.timeRollType = timeRollType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
