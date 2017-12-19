package com.liuhao.task.entity;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 14:21
 * @Description:
 * key的字符串解析转换类
 **/
public class TimeTaskConfig implements Serializable{

    private static final long serialVersionUID = 9118374600816668458L;

    private String clazz;

    private String attr;

    public TimeTaskConfig() {}

    public TimeTaskConfig(String clazz, String attr) {
        this.clazz = clazz;
        this.attr = attr;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
