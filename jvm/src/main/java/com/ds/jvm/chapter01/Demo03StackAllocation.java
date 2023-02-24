package com.ds.jvm.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/8 23:37
 * @Description 逃逸分析 栈上分配测试
 *            关闭逃逸分析   -Xms1G -Xmx1G  -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 *     (默认) 开启逃逸分析   -Xms1G -Xmx1G  -XX:+DoEscapeAnalysis  -XX:+PrintGCDetails
 *
 *            逃逸分析： 代码优化
 *                  1.栈上分配
 *                  2.同步省略
 *                  3.标量替换或分离对象
 */
public class Demo03StackAllocation {

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间 " + (end - start) + "ms");

        TimeUnit.SECONDS.sleep(1000);

    }

    private static void alloc() {
        User user = new User();
    }

    private static class User{

    }


}
