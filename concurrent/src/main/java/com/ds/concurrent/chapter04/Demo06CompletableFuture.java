package com.ds.concurrent.chapter04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ds
 * @date 2026/6/11
 * @description
 */
public class Demo06CompletableFuture {
    public static void main(String[] args) {
        try {
            String res = CompletableFuture.supplyAsync(() -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    System.out.println("e = " + e);
//                }
                return "1";
            }).get(2, TimeUnit.SECONDS);

            System.out.println("res = " + res);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
