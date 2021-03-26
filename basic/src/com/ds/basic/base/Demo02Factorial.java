package com.ds.basic.base;

/**
 * @Author ds
 * @Date 2021/3/24 17:14
 * @Description 递归求阶乘
 */
public class Demo02Factorial {

    public static void main(String[] args) {

        System.out.println(factorial(5));
    }

    public static int factorial(int i){
        if(i == 1){
            return 1;
        }
        System.out.println(i);
        return i * factorial(i - 1);
    }
}
