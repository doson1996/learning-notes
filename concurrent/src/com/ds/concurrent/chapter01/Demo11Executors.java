package com.ds.concurrent.chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/3/26 14:16
 * @Description
 *
 *  CachedThreadPool (当需要执⾏很多短时间的任务时，CacheThreadPool的线程复⽤率⽐较⾼， 会显著的提⾼性能。⽽且线程60s后会回收，意味着即使没有任务进来，CacheThreadPool并不会占⽤很多资源。)
 *      1. 提交任务进线程池。
 *      2. 因为corePoolSize为0的关系，不创建核⼼线程，线程池最⼤为Integer.MAX_VALUE。
 *      3. 尝试将任务添加到SynchronousQueue队列。
 *      4. 如果SynchronousQueue⼊列成功，等待被当前运⾏的线程空闲后拉取执⾏。如果当前没有空闲线程，那么就创建⼀个⾮核⼼线程，然后从SynchronousQueue拉取任务并在当前线程执⾏。
 *      5. 如果SynchronousQueue已有任务在等待，⼊列操作将会阻塞。
 *
 */
public class Demo11Executors {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService0 = Executors.newCachedThreadPool();
        /**
         * 运行中的 FixedThreadPool（未执行 shutdown()或 shutdownNow()）不会拒绝任务，在任务比较多的时候会导致 OOM（内存溢出）。
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);

        /**
         * SingleThreadExecutor 使用无界队列作为线程池的工作队列会对线程池带来的影响与 FixedThreadPool 相同。说简单点就是可能会导致 OOM，
         */
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        ExecutorService executorService3 = Executors.newScheduledThreadPool(10);


        String[] emails = {"1234@abc.com", "1235@abc.com", "1236@abc.com", "1237@abc.com", "1238@abc.com", "1239@abc.com", };
        List<String> list = Arrays.asList(emails);
        List<SendMail> sendMailTask = new ArrayList<>();
        for (String s : list) {
            sendMailTask.add( new SendMail(s));
        }

        for (SendMail task : sendMailTask) {
            Future<String> submit = executorService0.submit(task);
            String msg = submit.get();
            System.out.println(msg);
        }

        executorService0.shutdown();
    }

    private static class SendMail implements Callable<String> {

        private String email;

        public SendMail(String email){
            this.email = email;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            return "已发送邮件给" + email;
        }
    }
}
