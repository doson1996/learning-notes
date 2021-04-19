package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.ThreadUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author ds
 * @Date 2021/4/19 16:16
 * @Description
 */
public class Demo01Callable {

    public static void main(String[] args) throws Exception {
        System.out.println("start");

        TaskA taskA = new TaskA();
        TaskB taskB = new TaskB();

        FutureTask<Integer> fa = new FutureTask<>(taskA);
        FutureTask<String> fb = new FutureTask<>(taskB);

        new Thread(fa).start();
        new Thread(fb).start();

        Integer a = fa.get();
        String b = fb.get();

        System.out.println(a + b);
        System.out.println("end");
    }


    private static class TaskA implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            //模拟任务需要100毫秒
            ThreadUtils.milliseconds(100);
            System.out.println("a");
            return (int) (Math.random() * 100);
        }
    }

    private static class TaskB implements Callable<String> {

        @Override
        public String call() throws Exception {
            //模拟任务需要100毫秒
            ThreadUtils.milliseconds(200);
            System.out.println("b");
            return "task b returns the result";
        }
    }
}
