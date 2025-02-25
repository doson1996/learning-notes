package com.ds.basic.dynamicproxy.base.test;

import java.io.FileOutputStream;

import com.ds.basic.dynamicproxy.base.BaseProxy;
import com.ds.basic.dynamicproxy.base.CglibProxy;
import com.ds.basic.dynamicproxy.base.JdkProxy;
import com.ds.basic.dynamicproxy.base.ProxyFactory;
import lombok.SneakyThrows;
import sun.misc.ProxyGenerator;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class Test {
    public static void main(String[] args) {
        principle();

        // testJdk();
        // testCglib();
        //  testFactory();
    }

    /**
     * 原理
     * com.sun.proxy.$Proxy0
     */
    @SneakyThrows
    public static void principle() {
        // sun.misc.ProxyGenerator.generateProxyClass(java.lang.String, java.lang.Class<?>[])
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        BaseProxy jdkProxy = new JdkProxy();
        IUserService userService = (IUserService) jdkProxy.create(new UserServiceImpl());
        userService.say();
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{userService.getClass()});

        FileOutputStream os = new FileOutputStream("Proxy0.class");
        os.write(bytes);
        os.close();
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

    /**
     * cglib 不能代理final修饰的类
     */
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
