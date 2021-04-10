package com.ds.jvm.chapter01;

/**
 * @Author ds
 * @Date 2021/4/10 22:54
 * @Description 方法区 （jdk8 元空间[本地内存]  <8之前 永久代[虚拟机内存]>）
 *                  和堆一样，是各个线程共享的，在JVM启动时创建，JVM关闭时就会释放这个区域的内存
 *              jdk8设置方法区参数：
 *                  -XX:MetaspaceSize=100m  （应适当设置大一些，避免频繁的Full GC）
 *                      <设置分配的类元数据空间的大小，该类元数据空间将在首次超过该大小时触发垃圾回收>
 *                  -XX:MaxMetaspaceSize=100m
 *
 *              建议
 *                  1.MetaspaceSize和MaxMetaspaceSize设置一样大；
 *                  2.具体设置多大，建议稳定运行一段时间后通过jstat -gc pid确认且这个值大一些，对于大部分项目256m即可。
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
