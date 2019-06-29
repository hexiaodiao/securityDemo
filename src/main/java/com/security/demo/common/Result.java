package com.security.demo.common;

import com.security.demo.common.enumration.ResultCodeEnum;


/**
 * @author Relic
 * @date 2019/6/27 11:46
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public Result<T> setCode(ResultCodeEnum resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}