package com.liuhao.task.test.redis;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liuhao.task.time.TimeRoll;
import com.liuhao.task.time.TimeTask;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 18:16
 * @Description:
 **/
public class TimeTaskTest extends TimeTask implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(TimeTaskTest.class);

    private static final long serialVersionUID = 2542012364999094471L;

    public TimeTaskTest() {}

    public TimeTaskTest(TimeRoll timeRoll) {
        super(timeRoll);
    }

    @Override
    public Object execute() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("TimeTaskTest--------------------------------------------" + sdf.format(new Date()));

        return null;
    }

}
