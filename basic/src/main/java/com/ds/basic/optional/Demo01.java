package com.ds.basic.optional;

import java.util.Optional;

/**
 * @author ds
 * @date 2025/1/24
 * @description
 */
public class Demo01 {
    public static void main(String[] args) {
        Optional.of("a").ifPresent(System.out::println);
        Optional.ofNullable(null).ifPresent(System.out::println);
    }
}
