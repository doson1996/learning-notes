package com.ds.netty.chapter02netty.d8chat.service;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
public interface UserService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回 true, 否则返回 false
     */
    boolean login(String username, String password);

}
