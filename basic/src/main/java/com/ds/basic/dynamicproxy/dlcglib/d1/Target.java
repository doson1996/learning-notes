package com.ds.basic.dynamicproxy.dlcglib.d1;

/**
 * @author ds
 * @date 2024/1/15
 * @description
 */
public class Target {

    public void save() {
        System.out.println("save...");
    }

    public void save(int i) {
        System.out.println("save i..." + i);
    }

    public void save(String s) {
        System.out.println("save s..." + s);
    }

}
