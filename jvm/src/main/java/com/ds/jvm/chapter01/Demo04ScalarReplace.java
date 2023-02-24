package com.ds.jvm.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/8 23:52
 * @Description 逃逸分析 标量替换测试
 *            关闭逃逸分析 标量替换    -Xms1G -Xmx1G  -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:+PrintGCDetails
 *     (默认)  开启逃逸分析 标量替换    -Xms1G -Xmx1G  -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+PrintGCDetails
 *
 *            逃逸分析： 代码优化
 *                  1.栈上分配
 *                  2.同步省略
 *                  3.标量替换或分离对象
 *
 *            JDK7之后默认开启逃逸分析 .如果需要关闭逃逸分析 -XX:-DoEscapeAnalysis 即可，不推荐修改该参数。
 *            -XX:+EliminateAllocations 开启标量替换参数 . 该参数的前提是开启了逃逸分析，如果没有开启逃逸分析，仅开启该参数无效
 */
public class Demo04ScalarReplace {

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
        user.id = 1;
        user.name = "张三";
    }

    private static class User{
        public int id;
        public String name;
    }

}
