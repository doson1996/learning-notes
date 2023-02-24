package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.ThreadUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/19 17:02
 * @Description
 *  计数器必须大于等于 0，只是等于 0 时候，计数器就是零，调用 await 方法时不会阻塞当前线程。
 *  CountDownLatch 不可能重新初始化或者修改 CountDownLatch对象的内部计数器的值。
 *  一个线程调用 countDown 方法 happen-before，另外一个线程调用 await 方法
 */
public class Demo22CountDownLatch {
    /**
     *  CountDownLatch 的构造函数接收一个 int 类型的参数作为计数器，如果你想等待 N个点完成，这里就传入 N。
     *  当我们调用 CountDownLatch 的 countDown 方法时， N 就会减 1，CountDownLatch 的 await 方法会阻塞当前线程，
     *  直到 N 变成零。由于 countDown方法可以用在任何地方，所以这里说的 N 个点，可以是 N 个线程，也可以是 1 个线程里
     *  的 N 个执行步骤。用在多个线程时，只需要把这个 CountDownLatch 的引用传递到线程里即可。
     */
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");

        new Thread(() -> {
            System.out.println("1");
            c.countDown();
        }).start();

        new Thread(() -> {
            ThreadUtils.seconds(2);
            System.out.println("2");
            c.countDown();
        }).start();

        c.await();

        /**
         *
         * 如果有某个线程处理得比较慢，我们不可能让主线程一直等待，所以可
         * 以使用另外一个带指定时间的 await 方法——await（long time， TimeUnit unit），
         * 这个方法等待特定时间后，就会不再阻塞当前线程。 join 也有类似的方法。
         */
        //c.await(1L, TimeUnit.SECONDS);

        System.out.println("end");
    }
}
