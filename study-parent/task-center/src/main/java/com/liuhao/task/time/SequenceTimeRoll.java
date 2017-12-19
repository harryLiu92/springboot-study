package com.liuhao.task.time;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Author: liuhao
 * @Date: 2017/12/9 17:34
 * @Description:
 **/
public class SequenceTimeRoll extends TimeRoll implements Serializable {

    private static final long serialVersionUID = 8389927897331306101L;

    public SequenceTimeRoll() {
        super("0");
    }

    public SequenceTimeRoll(String time) {
        super(time);
    }

    @Override
    public int next() {
        return Integer.parseInt(getTime());
    }

    @Override
    public int getTimeRollType() {
        return TimeRollEnum.SEQUENCE.getType();
    }

}
