package com.liuhao.common.utils.dto;

import com.liuhao.common.utils.enums.ResponseResultEnum;

/**
 * @Author: liuhao
 * @Date: 2017/12/14 16:25
 * @Description:
 * RESULTFUL 风格
 **/
public class ResponseResult<T> {

    private int status = ResponseResultEnum.SUCCESS.getStatus();

    private String msg;

    private T result;

    public static boolean isSuccess(ResponseResult result) {
        return result != null && result.isSuccess();
    }

    public static boolean isError(ResponseResult result) {
        return result != null && result.isError();
    }

    public boolean isSuccess() {
        return ResponseResultEnum.isSuccess(status);
    }

    public boolean isError() {
        return ResponseResultEnum.isError(status);
    }

    public ResponseResult success(T result) {
        this.result = result;
        this.msg = "";
        this.status = ResponseResultEnum.SUCCESS.getStatus();
        return this;
    }

    public ResponseResult error(String msg) {
        this.msg = msg;
        this.result = null;
        this.status = ResponseResultEnum.ERROR.getStatus();
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
