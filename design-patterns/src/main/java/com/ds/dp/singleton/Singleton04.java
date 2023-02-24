package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/5 17:26
 * @Version 1.0
 * @Description 静态内部类
 *              线程安全，懒加载
 *
 */
public class Singleton04 {

    private Singleton04(){}

    private static class SingletonHolder{
        private static Singleton04 instance = new Singleton04();

    }

    public static Singleton04 getInstance(){
        return SingletonHolder.instance;
    }
}
