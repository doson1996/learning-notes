package com.ds.basic.innerclass;

/**
 * @Author ds
 * @Date 2021/3/17 10:21
 * @Description 静态内部类单例
 *              线程安全、懒加载
 */
public class Singleton {

    private Singleton(){}

    private static class SingletonHolder{
        public static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
