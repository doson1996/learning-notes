package com.ds.basic.string;

import java.util.StringJoiner;

/**
 * @Author ds
 * @Date 2021/3/15 9:32
 * @Description {@link StringBuffer} 和 {@link StringBuilder}比较
 */
public class Demo01StringBufferEqBuilder {

    /**
     * 每种方式测试次数
     */
    public static final int SIZE = 10000;

    public static void main(String[] args) {
        String str = "";
        /**
         *  进行字符串初始化,避免第一个执行测试的方式耗费创建String对象时间
         */
        for (int i = 0; i < SIZE; i++) {
            str = str + intToString(i);
        }
        //重新赋值为空字符串，不然str占用的空间越来越大，用下面的+进行拼接时所耗时间增加将近两倍
        str = "";

        /**
         * 用+拼接字符串
         */
        long startTime = getSystemTime();
        for (int i = 0; i < SIZE; i++) {
            str = str + intToString(i);
        }
        long endTime = getSystemTime();
        System.out.println("+==========" + calculation(startTime, endTime));

        //用stringJoiner拼接字符串
        startTime = getSystemTime();
        StringJoiner stringJoiner = new StringJoiner("");
        for (int i = 0; i < SIZE; i++) {
            stringJoiner.add(intToString(i));
        }
        endTime = getSystemTime();
        System.out.println("stringJoiner==========" + calculation(startTime, endTime));

        //用stringBuilder拼接字符串
        startTime = getSystemTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            stringBuilder.append(intToString(i));
        }
        endTime = getSystemTime();
        System.out.println("StringBuilder==========" + calculation(startTime, endTime));

        //用stringBuffer拼接字符串
        startTime = getSystemTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < SIZE; i++) {
            stringBuffer.append(intToString(i));
        }
        endTime = getSystemTime();
        System.out.println("StringBuffer==========" + calculation(startTime, endTime));

        //调用100000次的stringBuilder.toString()
        startTime = getSystemTime();
        for (int i = 0; i < SIZE; i++) {
            stringBuilder.toString();
        }
        endTime = getSystemTime();
        System.out.println("StringBuilderToString==========" + calculation(startTime, endTime));

        //调用100000次的stringBuffer.toString()
        startTime = getSystemTime();
        for (int i = 0; i < SIZE; i++) {
            stringBuffer.toString();
        }
        endTime = getSystemTime();
        System.out.println("StringBufferToString==========" + calculation(startTime, endTime));

        /**
         * StringBuilderToString==========8819ms
         * StringBufferToString==========5ms
         * 因为StringBuffer会在toString时会去先判断toStringCache是否为空，
         * 而只有在调用append时StringBuffer的toStringCache才为空，
         * 所以在没有改变值的情况下调用toString，
         * StringBuffer比StringBuilder效率高很多
         */

        startTime = getSystemTime();
        for (int i = 0; i < SIZE; i++) {
            stringBuffer.append(i).toString();
        }
        endTime = getSystemTime();
        System.out.println("StringBufferAppendToString==========" + calculation(startTime, endTime));

        startTime = getSystemTime();
        for (int i = 0; i < SIZE; i++) {
            stringBuilder.append(i).toString();
        }
        endTime = getSystemTime();
        System.out.println("StringBuilderAppendToString==========" + calculation(startTime, endTime));

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * 计算结束时间和开始时间之间差距多少毫秒
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static String calculation(long startTime, long endTime) {
        return (endTime - startTime) + "ms";
    }

    /**
     * 把int转换为String
     *
     * @param i
     * @return
     */
    public static String intToString(Integer i) {
        return String.valueOf(i);
    }

}
