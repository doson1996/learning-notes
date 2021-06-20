package com.ds.jvm.chapter01;

/**
 * @author ds
 * @date 2021/6/20 21:49
 */
public class Demo16SystemGCTest {
    public static void main(String[] args) {
        new Demo16SystemGCTest();

        System.gc();  //提醒jvm的垃圾回收器执行gc，但不一定马上执行
     //   System.runFinalization();  //强制调用失去引用对象的finalize方法
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
