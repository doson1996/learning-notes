package com.ds.jvm.chapter01;

/**
 * -XX:+PrintCommandLineFlags 查看使用的那个垃圾回收器 jdk8 ParallelGC   jdk9 g1
 * -XX:+UseSerialGC 指定SerialGC (串行 ，单核cpu下适合使用)
 * -XX:+UseG1GC 指定G1GC
 * -XX:+UseConcMarkSweepGC 指定老年代使用CMS，同时指定新生代使用 ParNewGC
 * @author ds
 * @date 2021/6/27 23:27
 */
public class Demo19UseGC {


    public static void main(String[] args) {
        /**
         * CMS   jdk9 被标记为   @Deprecated
         *       jdk 14 被删除
         *     优点
         *      1.并发收集
         *      2.低延迟
         *     缺点
         *      1.会产生内存碎片
         *      2.对cpu资源非常敏感
         *      3.无法处理浮动垃圾
         */
    }
}
