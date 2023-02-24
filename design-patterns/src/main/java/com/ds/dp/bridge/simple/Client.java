package com.ds.dp.bridge.simple;

/**
 * @Author ds
 * @Date 2021/4/13 15:51
 * @Description 桥接模式 （本质：分离抽象和实现）
 *                  优点：
 *                      1.分离抽象和实现部分
 *                      2.更好的扩展性
 *                      3.可动态的切换实现
 *                      4.可减少子类的个数
 */
public class Client {

    public static void main(String[] args) {

        Implementor implementor = new ConcreteImplementorB();

        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();

    }
}
