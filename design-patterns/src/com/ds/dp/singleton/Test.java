package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/5 17:22
 * @Version 1.0
 * @Description 单例模式测试
 *              使用场景： 工具类
 */
public class Test {

    public static void main(String[] args) {

        //未使用单例模式读取配置文件
        AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        System.out.println(appConfig1 == appConfig2);

        //饿汉式
        Singleton01 singleton011 = Singleton01.getInstance();
        Singleton01 singleton012 = Singleton01.getInstance();
        System.out.println(singleton011 == singleton012);

        //懒汉式 --非线程安全
        Singleton02 singleton021 = Singleton02.getInstance();
        Singleton02 singleton022 = Singleton02.getInstance();
        System.out.println(singleton021 == singleton022);

        //懒汉式 --线程安全
        Singleton03 singleton031 = Singleton03.getInstance();
        Singleton03 singleton032 = Singleton03.getInstance();
        System.out.println(singleton031 == singleton032);

        //静态内部类
        Singleton04 singleton041 = Singleton04.getInstance();
        Singleton04 singleton042 = Singleton04.getInstance();
        System.out.println(singleton041 == singleton042);

        //枚举
        Singleton05 singleton051 = Singleton05.instance;
        Singleton05 singleton052 = Singleton05.instance;
        singleton051.say("枚举实现单例");
        System.out.println(singleton051 == singleton052);


    }
}
