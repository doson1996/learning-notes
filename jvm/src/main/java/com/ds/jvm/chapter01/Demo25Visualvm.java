package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 *  -Xms=600m -Xmx=600m
 * @author ds
 * @date 2022/1/24 22:50
 */
public class Demo25Visualvm {
    public static void main(String[] args) {
        List<Pic> list = new ArrayList<>();
        while (true) {
            list.add(new Pic(Math.round(2)));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Pic {

    private byte[] buff;

    public Pic(int size) {
        this.buff = new byte[size * 1024 * 1024];
    }

}