package com.ds.concurrent.chapter02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Author ds
 * @Date 2021/4/16 16:22
 * @Description Fork/Join 框架是一个用于并行执行任务的框架，是一个把大任务分割
 * 成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架
 * jdk8 并行流  parallelStream
 */
public class Demo17ForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算 1+2+3+4
        CountTask task = new CountTask(1, 100);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }

    }

    private static class CountTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 2; // 阈值
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            // 如果任务足够小就计算任务
            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                // 如果任务大于阈值，就分裂成两个子任务计算
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                // 执行子任务
                leftTask.fork();
                rightTask.fork();
                // 等待子任务执行完，并得到其结果
                int leftResult = leftTask.join();
                int rightResult = rightTask.join();
                // 合并子任务
                sum = leftResult + rightResult;
            }
            return sum;
        }
    }

}
