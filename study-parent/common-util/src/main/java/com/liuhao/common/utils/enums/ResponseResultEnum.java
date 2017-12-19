package com.liuhao.common.utils.enums;

import com.liuhao.common.utils.dto.ResponseResult;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 16:30
 * @Description:
 **/
public enum ResponseResultEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "失败");

    int status;
    String desc;

    public static boolean isSuccess(int status) {
        return status == SUCCESS.getStatus()? true : false;
    }

    public static boolean isError(int status) {
        return status == ERROR.getStatus()? true : false;
    }

    ResponseResultEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
