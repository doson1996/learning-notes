package com.ds.jvm.chapter01;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * -Xmx512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -version
 * @author ds
 * @date 2022/3/6 22:38
 */
public class Demo27Monitor {

    private static final int MB = 1 * 1024 * 1024;

    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("初始堆大小: " + heapMemoryUsage.getInit() / MB + "m");
        System.out.println("最大堆大小: " + heapMemoryUsage.getMax() / MB + "m");
        System.out.println("已用堆大小: " + heapMemoryUsage.getUsed() / MB + "m");
        System.out.println("已用堆大小: " + heapMemoryUsage.getCommitted() / MB + "m");
        System.out.println("--------------------------");
        System.out.println("最大堆大小: " + Runtime.getRuntime().maxMemory() / MB + "m");
        System.out.println("可用堆大小: " + Runtime.getRuntime().freeMemory() / MB + "m");
    }
}
