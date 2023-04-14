package com.ds.basic.dynamicproxy.base.test;

import com.ds.basic.dynamicproxy.base.BaseProxy;
import com.ds.basic.dynamicproxy.base.CglibProxy;
import com.ds.basic.dynamicproxy.base.JdkProxy;
import com.ds.basic.dynamicproxy.base.ProxyFactory;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class Test {
    public static void main(String[] args) {
       // testJdk();
        testCglib();
      //  testFactory();
    }

    private static void testJdk() {
        BaseProxy jdkProxy = new JdkProxy();
        IUserService userService1 = (IUserService) jdkProxy.create(new UserServiceImpl());
        userService1.say();

        /**
         * UserService没有实现接口，jdk动态代理会报异常
         */
        UserService userService2 = (UserService) jdkProxy.create(new UserService());
        userService2.say();
    }

    private static void testCglib() {
        BaseProxy cglibProxy = new CglibProxy();
        IUserService userService1 = (IUserService) cglibProxy.create(new UserServiceImpl());
        userService1.say();

        UserService userService2 = (UserService) cglibProxy.create(new UserService());
        userService2.say();
    }

    private static void testFactory() {
        BaseProxy proxyFactory = new ProxyFactory();
        IUserService userService1 = (IUserService) proxyFactory.create(new UserServiceImpl());
        userService1.say();

        UserService userService2 = (UserService) proxyFactory.create(new UserService());
        userService2.say();
    }

}
