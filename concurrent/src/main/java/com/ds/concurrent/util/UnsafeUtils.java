package com.ds.concurrent.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import sun.misc.Unsafe;

/**
 * @author ds
 * @date 2026/3/26
 * @description
 */
public class UnsafeUtils {

    private static final Unsafe UNSAFE;

    static {
        try {
            Class<Unsafe> unsafeClass = Unsafe.class;
            Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafeField.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }

    public static void main(String[] args) {
        System.out.println("1");
        getUnsafe().park(false, 2000000);
        System.out.println("2");
    }

}
