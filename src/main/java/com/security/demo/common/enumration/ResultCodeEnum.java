package com.security.demo.common.enumration;

/**
 * @author Relic
 * @date 2019/6/27 11:59
 */
public enum ResultCodeEnum {
    //请求成功
    SUCCESS(1000),

    FAIL(2000),
    //未认证（签名错误）
    UNAUTHORIZED(403),
    //接口不存在
    NOT_FOUND(404),
    //服务器错误
    INTERNAL_SERVER_ERROR(500),
    //token不存在或者失效
    TOKEN_FAIL(401);

    public int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
