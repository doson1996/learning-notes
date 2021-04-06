package com.ds.dp.flyweight.simple;

/**
 * @Author ds
 * @Date 2021/4/6 10:15
 * @Description 享元模式 （分离与共享）
 *                  优点:
 *                      1.减少对象数量，节省内存空间
 *                  缺点：
 *                      2.维护共享对象，需要额外开销
 */
public class Client {

    public static void main(String[] args) {
        
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight test1 = factory.getFlyweight("test");
        Flyweight test2 = factory.getFlyweight("test");
        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);

    }
}
