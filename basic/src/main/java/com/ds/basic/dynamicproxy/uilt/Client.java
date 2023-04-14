package com.ds.basic.dynamicproxy.uilt;

import com.ds.basic.dynamicproxy.demo.UserService;
import com.ds.basic.dynamicproxy.demo.UserServiceImpl;

/**
 * @author ds
 */
public class Client {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Invoker<UserService> invoker = new Invoker<>(userService);
        UserService proxy = invoker.newProxy();
        proxy.save();
    }

}
