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

        /**
         * G1GC （混合收集）
         *      优点： 支持很大的堆，高吞吐量，支持可预测停顿
         *
         *       相比CMS， G1的优点有很多， 暂且不论可以指定最大停顿时间、 分Region的内存布局、 按收益动
         *       态确定回收集这些创新性设计带来的红利， 单从最传统的算法理论上看， G1也更有发展潜力。 与CMS
         *       的“标记-清除”算法不同， G1从整体来看是基于“标记-整理”算法实现的收集器， 但从局部（两个Region
         *       之间） 上看又是基于“标记-复制”算法实现， 无论如何， 这两种算法都意味着G1运作期间不会产生内存
         *       空间碎片， 垃圾收集完成之后能提供规整的可用内存。 这种特性有利于程序长时间运行， 在程序为大
         *      对象分配内存时不容易因无法找到连续内存空间而提前触发下一次收集。
         *
         *      缺点： 占用内存更多（10%-20%  记忆集）
         *
         *      不过， G1相对于CMS仍然不是占全方位、 压倒性优势的， 从它出现几年仍不能在所有应用场景中代替CMS就可以得知这个结论。 比起CMS， G1的弱项也可以列举出不少， 如在用户程序运行过程
         *      中， G1无论是为了垃圾收集产生的内存占用（Footprint） 还是程序运行时的额外执行负载
         *       （Overload） 都要比CMS要高。
         */
    }
}
