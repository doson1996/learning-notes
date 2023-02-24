package com.ds.jvm.chapter01;

public class Demo02GcTest {

    public static void main(String[] args) {

        System.gc();

        Runtime.getRuntime().gc();

    }
}
