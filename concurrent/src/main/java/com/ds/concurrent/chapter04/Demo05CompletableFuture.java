package com.ds.concurrent.chapter04;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * @author ds
 * @date 2025/8/15
 * @description
 */
public class Demo05CompletableFuture {
    public static void main(String[] args) throws Exception {
        CompletableFuture.supplyAsync(() -> {
                    // 异步任务1：获取用户数据
                    System.out.println(LocalDateTime.now() + "异步任务1：获取用户数据");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "张三";
                })
                .thenApplyAsync(user -> {
                    // 回调：处理用户数据
                    System.out.println(LocalDateTime.now() + "回调：处理用户数据：" + user);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "订单1";
                })
                .thenAcceptAsync(order -> {
                    System.out.println(LocalDateTime.now() + "回调：处理订单数据：" + order);
                })
                .exceptionally(ex -> {
                    // 统一错误处理回调
                    System.err.println("处理失败: " + ex.getMessage());
                    return null;
                });

        Thread.sleep(5000);
    }
}
