package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms256m -Xmx256m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:H:\gc.log
 * @author ds
 * @date 2022/3/7 21:42
 */
public class Demo28GCLog {
    private byte[] mb = new byte[50];

    public static void main(String[] args) {
        List<Demo01Heap> list = new ArrayList<>();
        while (true){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Demo01Heap());
        }
    }
}
