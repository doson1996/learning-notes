package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:StringTableSize=2000
 * -XX:MetaspaceSize=16m -XX:MaxMetaspaceSize=16m -Xms16m -Xmx16m
 * jps
 * jinfo -flag StringTableSize 4324[pid]
 *
 *  StringTable 长度
 *  jdk6 1009    6及之前放在永久代
 *  jdk7 60013   7及之后放在堆
 *  jdk8 60013最小1009
 * @author ds
 */
public class Demo10String {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space 证明jdk8放在堆中
        }
    }
}
