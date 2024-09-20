package com.ds.concurrent.chapter02;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/4/21 11:49
 * @Description Java 中的线程池是运用场景最多的并发框架，几乎所有需要异步或并发执行任务程序都可以使用线程池。在开发过程中，合理地使用线程池能够带来 3 个好处。
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制地创建，不仅会消耗 统资源，还会降低系统的稳定性，使用线程池可以进行统一分配、调优和监控。
 * <p>
 * <p>
 * ThreadPoolExecutor 执行 execute 方法分下面 4 种情况。
 * ⚫ 如果当前运行的线程少于 corePoolSize，则创建新线程来执行任务（注意，执行这一步骤需要获取全局锁）。
 * ⚫ 如果运行的线程等于或多于 corePoolSize，则将任务加入 BlockingQueue。
 * ⚫ 如果无法将任务加入 BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。
 * ⚫ 如果创建新线程将使当前运行的线程超出 maximumPoolSize，任务将被拒绝，并调用 RejectedExecutionHandler.rejectedExecution()方法。
 */
public class Demo26ThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * corePoolSize（线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，
         * 即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。
         * Runtime.getRuntime().availableProcessors();
         */
        int corePoolSize = 10;

        /**
         * maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，
         * 则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界的任务队列这个参数就没什么效果
         */
        int maximumPoolSize = 10;

        /**
         * keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。
         * 所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率。
         */
        long keepAliveTime = 0L;

        /**
         * unit（线程活动保持时间的单位）
         */
        TimeUnit unit = TimeUnit.SECONDS;

        /**
         *  workQueue (任务队列)：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列。
         *      ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
         *      LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按 FIFO 排序元素，吞吐量通常要高于 ArrayBlockingQueue。
         *                          静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
         *      SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，
         *                       吞吐量通常要高于Linked-BlockingQueue，静态工厂方法 Executors.newCachedThreadPool 使用了这个队列。
         *      PriorityBlockingQueue：一个具有优先级的无限阻塞队列
         */
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);

        /**
         * ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。
         * 使用开源框架 guava 提供的 ThreadFactoryBuilder 可以快速给线程池里的线程设置有意义的名字，代码如下。
         *  new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
         */
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        /**
         *  RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。
         *  这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。
         *  在 JDK 1.5 中 Java 线程池框架提供了以下 4 种策略。
         *      • AbortPolicy：直接抛出异常。
         *      • CallerRunsPolicy：只用调用者所在线程来运行任务。
         *      • DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
         *      • DiscardPolicy：不处理，丢弃掉。
         * 当然，也可以根据应用场景需要来实现 RejectedExecutionHandler 接口自定义策略。如记录日志或持久化存储不能处理的任务。
         * {@link CustomizePolicy]
         */
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        //    handler = new CustomizePolicy();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );

        // 线程池提前创建并启动所有基本线程
        // threadPool.prestartCoreThread();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            threadPool.submit(new Task());
            //  Integer res = threadPool.submit(new Task()).get();
            //  System.out.println("res = " + res);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 " + (end - start) + "ms");
        threadPool.shutdown();
    }


    private static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(200);
            System.out.println("task" + new Date().toLocaleString());
            return (int) (Math.random() * 10);
        }
    }

}
