package com.ds.concurrent.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ds
 * @Date 2021/4/19 13:39
 * @Description ⚫ AtomicBoolean：原子更新布尔类型。
 * ⚫ AtomicInteger：原子更新整型。
 * ⚫ AtomicLong：原子更新长整型。
 */
public class Demo18AtomicInteger {

    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {

        //addAndGet 以原子方式将输入的数值与实例中的值（AtomicInteger 里的 value）相加，并返回结果
        System.out.println(ai.addAndGet(1));

        //getAndIncrement 以原子方式将当前值加 1，注意，这里返回的是自增前的值。
        System.out.println(ai.getAndIncrement());

        //compareAndSet 如果输入的数值等于预期值，则以原子方式将该值设置为输入的值
        System.out.println(ai.compareAndSet(1, 5));
        System.out.println(ai.compareAndSet(2, 5));
        System.out.println(ai.get());

        ai.lazySet(10);
        System.out.println(ai.get());

    }
}
