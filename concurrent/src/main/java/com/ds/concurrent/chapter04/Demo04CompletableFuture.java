package com.ds.concurrent.chapter04;

import com.ds.concurrent.threadpool.LogPolicy;
import com.ds.concurrent.threadpool.NameThreadFactory;
import com.ds.concurrent.util.SleepUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/4/10
 * @description
 */
public class Demo04CompletableFuture {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new NameThreadFactory("ds"),
                new LogPolicy()
        );

        CompletableFuture<String> cf1 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("step1执行...");
                    return "step1 result";
                }, threadPool);

        CompletableFuture<String> cf2 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("step2执行...");
                    return "step2 result";
                }, threadPool);

        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + " ," + result2);
            System.out.println("执行 step 3");
            return "step3 result";
        }).thenAccept(System.out::println);

    }
}
