package com.ds.dp.composite.unused;

/**
 * @Author ds
 * @Date 2021/3/24 15:55
 * @Description 不使用组合模式的解决办法
 *              必需要区别对待叶子对象和组合对象
 */
public class Client {

    public static void main(String[] args) {

        Composite root = new Composite("服装");
        Composite c1 = new Composite("男装");
        Composite c2 = new Composite("女装");

        Leaf leaf1 = new Leaf("男装1");
        Leaf leaf2 = new Leaf("男装2");
        Leaf leaf3 = new Leaf("女装1");
        Leaf leaf4 = new Leaf("女装2");

        root.addChildComposite(c1);
        root.addChildComposite(c2);

        c1.addChildLeaf(leaf1);
        c1.addChildLeaf(leaf2);
        c2.addChildLeaf(leaf3);
        c2.addChildLeaf(leaf4);

        root.printStruct("");
    }
}
