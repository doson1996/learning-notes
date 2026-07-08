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
        System.out.println(tableSizeFor(33));

        int tableLength = tableSizeFor(16);
        int hash1 = 15;
        int hash2 = 31;
        int i11 = hash1 % tableLength;
        System.out.println("i11 = " + i11);
        // 等于 hash2 % tableLength
        // 11111 (31)
        // 01111 (15)
        int i21 = hash2 & (tableLength - 1);
        System.out.println("i21 = " + i21);
       // 10000
       // 01111
        int i12 = tableLength & hash1;
        System.out.println("i12 = " + i12);
        int i22 = tableLength & hash2;
        System.out.println("i22 = " + i22);
        // tableLength作为2的次方扩容一倍后，hash值不用重新计算
        tableLength = tableSizeFor(32);
        i11 = hash1 % tableLength;
        System.out.println("i11 = " + i11);
        i21 = hash2 % tableLength;
        System.out.println("i21 = " + i21);
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
