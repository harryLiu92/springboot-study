package com.home.web.builder;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 14:13
 * @Description:
 **/
public class UserInfoBuilder {

    public static String builder(Object object) {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append((String) FieldUtils.readDeclaredField(object, "nickName", true));
            builder.append((String) FieldUtils.readDeclaredField(object, "gender", true));
            builder.append((String) FieldUtils.readDeclaredField(object, "language", true));
            builder.append((String) FieldUtils.readDeclaredField(object, "city", true));
            builder.append((String) FieldUtils.readDeclaredField(object, "province", true));
            builder.append((String) FieldUtils.readDeclaredField(object, "avatarUrl", true));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
