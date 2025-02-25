package com.ds.basic.base;

/**
 * @author ds
 * @description {@link Integer.IntegerCache} 中缓存了 -128 到 127
 * -XX:AutoBoxCacheMax=1024
 * 在不设置时缓存了 -128 到 127 下面的结果为 false true
 * 在设置时缓存了 -128 到 1024 下面的结果为 true true
 */
public class Demo01Integer {

    public static void main(String[] args) {

        Integer i1 = 130;
        Integer i2 = 130;
        System.out.println(i1 == i2);

        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println(i3 == i4);

    }
}
