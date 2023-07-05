package com.ds.netty.chapter02netty.d8chat.service;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
public class UserServiceFactory {
    private static final UserService userService = new UserServiceMemoryImpl();

    public static UserService getUserService() {
        return userService;
    }
}
