package com.ds.basic.string;

/**
 * @Author ds
 * @Date 2021/3/15 9:32
 * @Description
 */
public class Demo01String {

    public static void main(String[] args) {
        StringBuffer sb1 = new StringBuffer();
        StringBuilder sb2 = new StringBuilder();

        String str = "";
        sb1.append("1").append(str);
        System.out.println(sb1.toString());
    }
}
