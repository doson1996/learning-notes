package com.ds.jvm.chapter01;

/**
 * -XX:+PrintCommandLineFlags 查看使用的那个垃圾回收器 jdk8 ParallelGC   jdk9 g1
 * -XX:+UseSerialGC 指定SerialGC (串行 ，单核cpu下适合使用)
 * -XX:+UseG1GC 指定G1GC
 * @author ds
 * @date 2021/6/27 23:27
 */
public class Demo19UseGC {
    public static void main(String[] args) {
        System.out.println("");
    }
}
