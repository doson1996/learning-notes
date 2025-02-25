package com.ds.dp.decorator.simple;


/**
 * @Author ds
 * @Date 2021/4/8 11:35
 * @Description 装饰者模式 （本质：动态组合）   <思想上和aop有共同之处>
 * 优点：
 * 1.比继承更灵活
 * 2.更容易复用功能
 * 3.简化高层定义
 * 缺点：
 * 1.会产生很多细粒度的对象
 */
public class Client {

    public static void main(String[] args) {

        Component component = new ConcreteComponent();
        component.operation();

        Decorator decorator = new ConcreteDecoratorB(component);
        decorator.operation();


    }
}
