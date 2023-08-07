package com.ds.concurrent.chapter03;

/**
 * @author ds
 * @date 2023/8/7
 * @description
 */
public class Demo05Synchronized {

    public static void main(String[] args) {

    }

    /**
     * synchronized 修饰的方法并没有 monitorenter 指令和 monitorexit 指令，
     * 取得代之的确实是 ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法。
     */
    public static synchronized void say() {

    }

    /**
     * synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，
     * 其中 monitorenter 指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置。
     */
    public static void sayHello() {
        synchronized (Demo05Synchronized.class) {
            System.out.println("hello...");
        }
    }

}
