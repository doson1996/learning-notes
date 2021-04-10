package com.ds.jvm.chapter01;

/**
 * @Author ds
 * @Date 2021/4/10 22:54
 * @Description 方法区 （jdk8 元空间[本地内存]  <8之前 永久代[虚拟机内存]>）
 *                  和堆一样，是各个线程共享的，在JVM启动时创建，JVM关闭时就会释放这个区域的内存
 *              jdk8设置方法区大小参数：
 *                  -XX:MetaspaceSize=100m  （应适当设置大一些，避免频繁的Full GC）
 *                  -XX:MaxMetaspaceSize=100m
 *
 *             方法区也有OOM
 *                  1.加载过多第三方jar包
 *                  2.Tomcat部署过多应用
 *                  3.大量动态的生成反射类
 */
public class Demo05MethodArea {

    public static void main(String[] args) {

        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
