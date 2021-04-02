package com.ds.concurrent.util;

/**
 * @Author ds
 * @Date 2021/4/2 14:29
 * @Description
 */
public class ThreadUtils extends SleepUtils{

   private ThreadUtils(){};

    /**
     *
     * @param lock
     */
   public static void wait(Object lock){
       try {
           lock.wait();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

    public static void notify(Object lock){
        lock.notify();
    }

    public static void notifyAll(Object lock){
        lock.notifyAll();
    }
}
