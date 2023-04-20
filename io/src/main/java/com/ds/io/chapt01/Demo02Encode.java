package com.ds.io.chapt01;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author ds
 * @date 2023/4/19
 * @description ASCII
 *                  一个英文占一个字节
 *              GBK 完全兼容ASCII
 *                  一个英文占一个字节，二进制第一位是0
 *                  一个中文占两个字节，高位二进制第一位是1
 *              Unicode字符集utf-8编码格式
 *                  一个英文占一个字节，二进制第一位是0
 *                  一个中文占三个字节，二进制第一位是1
 */
public class Demo02Encode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /* 编码 */
        String str = "ab张三";
        byte[] bytes = str.getBytes();
        // idea默认utf-8, 两个英文(2)，两个汉字(6)，8个字节
        System.out.println("bytes = " + Arrays.toString(bytes));

        byte[] bytes1 = str.getBytes("GBK");
        //GBK,两个英文(2)，两个汉字(4)，6个字节
        System.out.println("bytes1 = " + Arrays.toString(bytes1));

        /* 解码 */
        String s1 = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("s1 = " + s1);

        String s2 = new String(bytes, "GBK");
        System.out.println("s2 = " + s2);
    }
}
