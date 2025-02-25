package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:47
 * @Description 抽象工厂
 * 优点:                     缺点：
 * 1.分离接口和实现            1.不易扩展
 * 2.切换产品簇更方便          2.容易造成类层次更复杂
 */
public class AbstractFactoryClient {

    public static void main(String[] args) {

        //simpleFactory();
        abstractFactory();
    }

    /**
     * 简单工厂来解决组装电脑
     * cpu和主板应该是配对的，随意选择会造成不匹配，如
     * computer.makeComputer(1,2);
     */
    public static void simpleFactory() {

        Computer computer = new Computer();
        computer.makeComputer(1, 1);
        computer.makeComputer(1, 2);
    }

    /**
     * 抽象工厂
     */
    public static void abstractFactory() {

        Computer computer = new Computer();
        computer.makeComputer(new Dell01());
        computer.makeComputer(new Hp01());
    }
}
