package com.ds.dp.composite.simple;

/**
 * @Author ds
 * @Date 2021/3/24 15:55
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        Component root = new Composite();
        Component c1 = new Composite();
        Component c2 = new Composite();

        Component leaf1 = new Leaf();
        Component leaf2 = new Leaf();
        Component leaf3 = new Leaf();
        Component leaf4 = new Leaf();

        root.addChild(c1);
        root.addChild(c2);

        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c2.addChild(leaf3);
        c2.addChild(leaf4);


        Component child0 = root.getChild(0);
        System.out.println(child0);

        Component child1 = root.getChild(1);
        System.out.println(child1);

        Component child2 = root.getChild(2);
        System.out.println(child2);
    }
}
