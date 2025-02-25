package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/5 17:26
 * @Version 1.0
 * @Description 懒汉式
 * 非线程安全
 */
public class Singleton03 {

    private static Singleton03 instance = null;

    private Singleton03() {
    }

    public static synchronized Singleton03 getInstance() {
        if (instance == null) {
            instance = new Singleton03();
        }
        return instance;
    }
}
