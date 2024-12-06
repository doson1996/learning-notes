package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/10/28
 * @description -Xms256m -Xmx256m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xloggc:D:\gc.log
 */
public class Demo20ListGC {
    public static void main(String[] args) {
        List<Demo01Heap> list = new ArrayList<>();
        int i = 1;
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + i + "次");
            list.add(new Demo01Heap());
            if (list.size() > 10) {
                list.set(i - 2, null);
            }
            i++;
        }
    }
}
