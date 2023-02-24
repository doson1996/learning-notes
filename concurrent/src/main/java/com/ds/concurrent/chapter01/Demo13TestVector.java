package com.ds.concurrent.chapter01;

import java.util.Vector;

/**
 * @Author ds
 * @Date 2021/3/29 14:19
 * @Description
 */
public class Demo13TestVector {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        vector.add(1);
        vector.add(2);

        Thread threadA = new Thread(new ThreadA());
        Thread threadB = new Thread(new ThreadB());

        threadA.start();
        //这里设置的sleep时间要比getLast的小，更容易触发ArrayIndexOutOfBoundsException
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();


    }

    private static Integer getLast(){
        int lastIndex = vector.size() - 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getList---" + lastIndex);
        return vector.get(lastIndex);
    }

    private static void removeLast(){
        int lastIndex = vector.size() - 1;
        System.out.println("removeLast---" + lastIndex);
        vector.remove(lastIndex);
    }

    private static synchronized Integer getLast1(){
        int lastIndex = vector.size() - 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getList---" + lastIndex);
        return vector.get(lastIndex);
    }

    private static synchronized void removeLast1(){
        int lastIndex = vector.size() - 1;
        System.out.println("removeLast---" + lastIndex);
        vector.remove(lastIndex);
    }


    private static class ThreadA implements Runnable{

        @Override
        public void run() {
            Integer last = getLast();
         //   Integer last = getLast1();
            System.out.println("last = " + last);
        }
    }

    private static class ThreadB implements Runnable{

        @Override
        public void run() {
            removeLast();
          //  removeLast1();
        }
    }
}
