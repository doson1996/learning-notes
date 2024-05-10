package com.ds.basic.collection.map.hashmap;

/**
 * @Author ds
 * @Date 2021/3/19 15:18
 * @Description
 */
public class Demo01 {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {

       /* HashMap<String,Object> map = new HashMap<>();
        map.put("a","a");*/
        System.out.println(tableSizeFor(4));

    }


    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
