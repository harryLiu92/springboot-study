package com.liuhao.task.time;

public enum TimeRollEnum {

    /**
     * 指定时间
     */
    APPOINT(1),
    /**
     * 连续时间
     */
    SEQUENCE(2);

    TimeRollEnum(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
