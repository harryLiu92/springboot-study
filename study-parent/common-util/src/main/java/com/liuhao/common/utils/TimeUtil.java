package com.liuhao.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Administrator liuhao
 * 2017/12/12 22:12
 **/
public class TimeUtil {

    private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    public static Date now() {
        return new Date();
    }

    public static String yyyyMMddHHmmssNow() {
        return yyyyMMddHHmmss(new Date());
    }

    public static String yyyyMMddNow() {
        return yyyyMMdd(new Date());
    }

    public static Date yyyyMMddHHmmss(String text) {

        Date date = null;
        try {
            date = yyyyMMddHHmmss.parse(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String yyyyMMddHHmmss(Date date) {

        String text = null;
        try {
            text = yyyyMMddHHmmss.format(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static Date yyyyMMdd(String text) {

        Date date = null;
        try {
            date = yyyyMMdd.parse(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String yyyyMMdd(Date date) {

        String text = null;
        try {
            text = yyyyMMdd.format(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
