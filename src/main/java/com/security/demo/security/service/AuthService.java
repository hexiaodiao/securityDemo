package com.security.demo.security.service;

/**
 * @author Relic
 * @date 2019/6/27 11:57
 */
public interface AuthService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return java.lang.String
     * @author you
     * @date 2019/1/7 15:09
     */
    String login(String username, String password);

    /**
     * 重新获取token
     *
     * @param oldToken 旧token
     * @return java.lang.String
     * @author you
     * @date 2019/1/7 15:09
     */
    String refresh(String oldToken);

    /**
     * 登出
     *
     * @param token token
     * @return boolean
     * @author you
     * @date 2019/1/8 14:38
     */
    boolean logout(String token);
}
