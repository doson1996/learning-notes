package com.ds.basic.dynamicproxy.demo;

/**
 * @Author ds
 * @Date 2021/3/19 9:51
 * @Description 动态代理
 */
public class Client {

    public static void main(String[] args) {

        /**
         * jdk动态代理测试
         */
        UserService userService = new UserServiceImpl();
        JdkProxy jdkProxy = new JdkProxy();
        UserService proxy = (UserService) jdkProxy.newProxy(userService);
        proxy.save();

        /**
         * cglib动态代理测试
         */
        CglibService cglibService = new CglibService();
        CglibProxy cglibProxy = new CglibProxy();
        CglibService cglib = (CglibService) cglibProxy.newProxy(cglibService);
        cglib.save();


    }
}
