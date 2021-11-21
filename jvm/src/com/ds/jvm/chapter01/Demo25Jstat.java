package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * jstat -gc
 *       -class
 *
 * -xms64m -xmx64m
 * @author ds
 */
public class Demo25Jstat {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[100 * 1024]; // 100kb
            list.add(arr);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
