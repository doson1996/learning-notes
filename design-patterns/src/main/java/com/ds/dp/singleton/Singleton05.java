package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/8 14:16
 * @Version 1.0
 * @Description 枚举实现单例
 */
public enum Singleton05 {
    /**
     * 实例
     */
    instance;

    public void say(String msg) {
        System.out.print(msg);
    }
}
