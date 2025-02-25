package com.ds.basic.base;

import java.util.Objects;

/**
 * @author ds
 * @date 2021/12/22 22:56
 */
public class Demo04Lambda2 {
    public static void main(String[] args) {
        ILambda lambda = null;

        lambda = (str) -> {
            if (Objects.isNull(str)) {
                return "null";
            }
            return str;
        };

        String str = lambda.test("a");
        System.out.println("str = " + str);
    }
}

interface ILambda {
    String test(String str);
}