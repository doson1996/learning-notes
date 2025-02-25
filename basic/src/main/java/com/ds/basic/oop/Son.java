package com.ds.basic.oop;

/**
 * @Author ds
 * @Date 2021/3/29 9:34
 * @Description
 */
public class Son implements Father {

    public int i = 10;

    @Override
    public void print() {
        System.out.println(i);
    }
}
