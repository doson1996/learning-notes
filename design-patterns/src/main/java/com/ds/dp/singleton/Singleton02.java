package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/5 17:26
 * @Version 1.0
 * @Description 懒汉式
 * 非线程安全(时间换空间)
 */
public class Singleton02 {

    private static Singleton02 instance = null;

    private Singleton02() {
    }

    public static Singleton02 getInstance() {
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }
}
