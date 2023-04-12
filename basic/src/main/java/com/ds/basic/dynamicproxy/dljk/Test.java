package com.ds.basic.dynamicproxy.dljk;

import com.ds.basic.dynamicproxy.UserService;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public class Test {
    public static void main(String[] args) {
        UserService userService = (UserService) new Invoker(UserService.class).getInstance();
        userService.save();
    }
}
