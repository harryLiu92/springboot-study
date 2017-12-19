package com.liuhao.task.time;

import com.liuhao.common.utils.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public abstract class TimeTask<T> implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(TimeTask.class);

    private TimeRoll timeRoll;

    public TimeTask() {}

    public TimeTask(TimeRoll timeRoll) {
        this.timeRoll = timeRoll;
    }

    public abstract T execute();

    public TimeRoll getTimeRoll() {
        return timeRoll;
    }

    public void setTimeRoll(TimeRoll timeRoll) {
        this.timeRoll = timeRoll;
    }

    @Override
    public ResponseResult call() {
        logger.info("---------------TimeTask.call.start timeRoll = {}-------------------", timeRoll);

        if (timeRoll == null) {
            throw new NullPointerException("TimeTask' timeRoll is null");
        }

        ResponseResult<T> result = new ResponseResult<T>();

        try {
            T t = execute();
            result.success(t);
        } catch (Throwable t) {
            result.error(t.getMessage());
        }
        logger.info("---------------TimeTask.call.end result= {}-------------------", result);
        return result;
    }

}
