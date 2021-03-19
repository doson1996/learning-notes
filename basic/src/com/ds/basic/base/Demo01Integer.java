package com.ds.basic.base;

/**
 * @Author ds
 * @Date 2021/3/17 16:44
 * @Description {@link Integer.IntegerCache} 中缓存了 -128 到 127
 *              false
 *              true
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
