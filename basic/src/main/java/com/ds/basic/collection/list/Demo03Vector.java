package com.ds.basic.collection.list;

import java.util.Vector;

/**
 * @Author ds
 * @Date 2021/5/11 14:35
 * @Description
 */
public class Demo03Vector {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();

        vector.add("1");
        vector.add("1");

        new Thread(() -> {
            del(vector);
        }).start();

        new Thread(() -> {
            del(vector);
        }).start();
    }

    private static void del(Vector vector) {
        int index = vector.size() - 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vector.remove(index);
    }
}
