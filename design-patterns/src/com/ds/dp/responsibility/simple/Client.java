package com.ds.dp.responsibility.simple;

/**
 * @Author ds
 * @Date 2021/4/9 9:44
 * @Description 职责链模式 （分离职责，）
 *                  优点：
 *                      1.请求者与接收者松散耦合
 *                      2.动态组合职责
 *                  缺点：
 *                      1.产生很多细粒度对象
 */
public class Client {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();

        handler1.setHandler(handler2);
        handler1.handlerRequest();
    }
}
