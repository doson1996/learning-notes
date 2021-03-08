package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/5 17:19
 * @Version 1.0
 * @Description 饿汉式
 *              线程安全，由于是一开始就加载，没有使用的话会造成浪费(空间换时间)
 *              JDK中使用 {@link Runtime}
 */
public class Singleton01 {

    private static Singleton01 instance = new Singleton01();

    private Singleton01(){}

    public static Singleton01 getInstance(){
        return instance;
    }
}
