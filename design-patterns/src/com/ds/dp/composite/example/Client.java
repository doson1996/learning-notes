package com.ds.dp.composite.example;

/**
 * @Author ds
 * @Date 2021/3/24 16:41
 * @Description 用组合模式解决
 *                  优点：
 *                      1.定义了包含基本对象和组合对象的类层次结构
 *                      2.统一了组合对象和叶子对象
 *                      3.简化了客户端调用
 *                      4.更容易扩展
 *                  缺点：
 *                      1.很难限制组合中的组件类型
 */
public class Client {

    public static void main(String[] args) {
        Component root = new Composite("服装");
        Component c1 = new Composite("男装");
        Component c2 = new Composite("女装");

        Component leaf1 = new Leaf("男装1");
        Component leaf2 = new Leaf("男装2");
        Component leaf3 = new Leaf("女装1");
        Component leaf4 = new Leaf("女装2");

        root.addChild(c1);
        root.addChild(c2);

        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c2.addChild(leaf3);
        c2.addChild(leaf4);

        root.printStruct("");

    }
}
