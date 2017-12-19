package com.liuhao.task.time;

import com.liuhao.common.utils.TimeUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2017/12/9 17:34
 * @Description:
 **/
public class AppointTimeRoll extends TimeRoll implements Serializable {

    /**
     * yyyy-mm-dd HH:MM:ss
     *   *可以替代任何位置
     */
    private static final String YYYYMMDDHHMMSS = "[\\d|*]{4}-[\\d|*]{2}-[\\d|*]{2} [\\d|*]{2}:[\\d|*]{2}:[\\d|*]{2}";

    public AppointTimeRoll() {
        super("****-**-** **:**:**");
    }

    public AppointTimeRoll(String time) {
        super(time);
    }

    @Override
    public int getTimeRollType() {
        return TimeRollEnum.APPOINT.getType();
    }

    @Override
    public int next() {
        if (! getTime().matches(YYYYMMDDHHMMSS)) {
            throw new IllegalArgumentException("yyyy-mm-dd HH:MM:ss *可以替换任意位置");
        }
        Date toDate = parseRegex();

        Date now = TimeUtil.now();

        long time = toDate.getTime() - now.getTime();

        return (int)time;
    }

    private Date parseRegex() {
        String now = TimeUtil.yyyyMMddHHmmssNow();

        String time = getTime();

        while (getTime().indexOf("*") != -1) {
            int index = time.indexOf("*");

            String word = String.valueOf(now.charAt(index));

            time = time.replaceFirst("\\*", word);
        }

        setTime(time);

        return TimeUtil.yyyyMMddHHmmss(time);
    }
}
