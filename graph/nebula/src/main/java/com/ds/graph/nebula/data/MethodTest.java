package com.ds.graph.nebula.data;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/11/28
 * @description
 */
public class MethodTest {
    public static void main(String[] args) {
        m1();
    }

    private static void m1() {
        m2();
    }

    private static void m2() {
//        try {
//            Method declaredMethod = MethodTest.class.getDeclaredMethod("m3");
//            declaredMethod.invoke(null);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        m3();
    }

    private static void m3() {
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println("stackTraceElement = " + stackTraceElement);
            int lineNumber = stackTraceElement.getLineNumber();
            System.out.println("lineNumber = " + lineNumber);
        }
    }

}
