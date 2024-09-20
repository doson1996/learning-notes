package com.ds.concurrent.chapter02;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author ds
 * @Date 2021/4/19 14:01
 * @Description ⚫ AtomicIntegerArray：原子更新整型数组里的元素。
 * ⚫ AtomicLongArray：原子更新长整型数组里的元素。
 * ⚫ AtomicReferenceArray：原子更新引用类型数组里的元素。
 */
public class Demo19AtomicIntegerArray {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2};
        AtomicIntegerArray ai = new AtomicIntegerArray(arr);
        ai.getAndAdd(1, 1);
        System.out.println("ai.get(1) = " + ai.get(1));

        ai.getAndSet(1, 5);
        System.out.println("ai.get(1) = " + ai.get(1));

        /**
         * 数组 value 通过构造方法传递进去，然后 AtomicIntegerArray 会将当前数组复制一份，
         * 所以当 AtomicIntegerArray 对内部的数组元素进行修改时，不会影响传入的数组
         */
        System.out.println(arr[1]);
    }
}
