package com.ds.basic.string;

/**
 * @author ds
 */
public class Demo03Cast {
    public static void main(String[] args) {
        Object s1 = null;
        String str = (String) s1;
        System.out.println("str = " + str);
    }
}
