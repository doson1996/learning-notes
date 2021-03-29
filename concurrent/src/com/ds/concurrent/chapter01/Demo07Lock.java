package com.ds.concurrent.chapter01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ds
 * @Date 2021/3/25 15:29
 * @Description 锁
 *         一、锁的四种状态：（级别由低到高）
 *              1.无锁状态
 *              2.偏向锁     (偏向锁在资源⽆竞争情况下消除了同步语句，连CAS操作都不做了，提⾼了程序的运⾏  <⼤⽩话就是对锁置个变量，如果发现为true，代表资源⽆竞争，则⽆需再⾛各种加锁/解锁流程。如果为false，代表存在其他线程竞争资源，那么就会⾛后⾯的流程>)
 *                            -> 偏向锁升级成轻量级锁时，会暂停拥有偏向锁的线程，重置偏向锁标识，这个过程看起来容易，实则开销还是很⼤的锁通常出于竞争状态，那么偏向锁就会是⼀种累赘
 *                            -> 关闭偏向锁 -XX:UseBiasedLocking=false。
 *              3.轻量级锁   (适应性⾃旋，简单来说就是线程如果⾃旋成功了，则下次⾃旋的次数会更多，如果⾃旋失败了，则⾃旋的次数就会减少。)
 *              4.重量级锁
 *          ⼏种锁会随着竞争情况逐渐升级，锁的升级很容易发⽣，但是锁降级发⽣的条件会⽐较苛刻，锁降级发⽣在Stop The World期间，
 *          当JVM进⼊安全点的时候，会检查是否有闲置的锁，然后进⾏降级
 *
 *        二、锁升级的过程
 *              1.第⼀步，检查MarkWord⾥⾯是不是放的自己的ThreadId ,如果是，表示当前线程是处于 “偏向锁”
 *              2.第二步，如果MarkWord不是⾃⼰的ThreadId，锁升级，这时候，⽤CAS来执⾏切换，
 *                      新的线程根据MarkWord⾥⾯现有的ThreadId，通知之前线程暂停，之前线程将MarkWord的内容置为空
 *              3.第三步，两个线程都把锁对象的HashCode复制到自己新建的⽤于存储锁的记录空间，接着开始通过CAS操作，
 *                      把锁对象的MarkWord的内容修改为自己新建的记录空间的地址的⽅式竞争MarkWord
 *              4.第四步，第三步中成功执⾏CAS的获得资源，失败的则进⼊自旋。
 *              5.第五步，自旋的线程在自旋过程中，成功获得资源(即之前获的资源的线程执⾏完成并释放了共享资源)，
 *                      则整个状态依然处于 轻量级锁的状态，如果自旋失败 。
 *              6.第六步，进⼊重量级锁的状态，这个时候，自旋的线程进⾏阻塞，等待之前线程执⾏完成并唤醒自己。
 *
 *
 *
 */
public class Demo07Lock {

    /**
     * 关键字在实例方法上，锁为当前实例
     */
    public synchronized void instanceLock(){
        //code
    }

    //等价于
    public void lock1(){
        synchronized (this){

        }
    }


    /**
     * 关键字在静态方法上，锁为当前class对象
     */
    public static synchronized void classLock(){
        //code
    }

    //等价于
    public void lock2(){
        synchronized (this.getClass()){

        }
    }

    public void blockLock(){
        Object lock = new Object();
        synchronized (lock){
            //code
        }
    }
}
