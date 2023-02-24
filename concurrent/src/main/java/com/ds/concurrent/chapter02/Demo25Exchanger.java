package com.ds.concurrent.chapter02;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/21 11:25
 * @Description
 *      Exchanger（交换者）是一个用于线程间协作的工具类。 Exchanger 用于进行线程间
 *      的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。这两
 *      个线程通过 exchange 方法交换数据，如果第一个线程先执行 exchange()方法，它会一直
 *      等待第二个线程也执行 exchange 方法，当两个线程都到达同步点时，这两个线程就可以
 *      交换数据，将本线程生产出来的数据传递给对方。下面来看一下 Exchanger 的应用场景。
 */
public class Demo25Exchanger {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        Exchanger<String> e = new Exchanger<>();

        threadPool.execute(() -> {
            try {
                /**
                 * 如果两个线程有一个没有执行 exchange()方法，则会一直等待，如果担心有特殊情
                 * 况发生，避免一直等待，可以使用 exchange（V x， longt imeout， TimeUnit unit）设置最
                 * 大等待时长。
                 */
                System.out.println("e.exchange(a) = " + e.exchange("A", 100L, TimeUnit.MILLISECONDS));

               // System.out.println("e.exchange(a) = " + e.exchange("A"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
           //     Thread.sleep(1000);
                System.out.println("e.exchange(b) = " + e.exchange("B", 100L, TimeUnit.MILLISECONDS));

                // System.out.println("e.exchange(b) = " + e.exchange("B"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        threadPool.shutdown();
    }
}
