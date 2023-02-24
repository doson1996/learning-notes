package com.ds.concurrent.util;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/1 17:42
 * @Description
 */
public class SleepUtils {

    protected SleepUtils(){}

    /**
     * 暂停线程 milliseconds 毫秒
     * @param milliseconds
     */
    public static void milliseconds(long milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停线程 seconds 秒
     * @param seconds
     */
    public static void seconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停线程 minutes 分钟
     * @param minutes
     */
    public static void minutes(long minutes){
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
