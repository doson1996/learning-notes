package com.ds.dp.visitor.simple;

/**
 * @Author ds
 * @Date 2021/4/14 13:50
 * @Description 访问者模式 （本质：预留通路，回调实现）<二次分发>
 *                  优点：
 *                     1.好的扩展性
 *                     2.好的复用性
 *                     3.分离无关行为
*                    缺点:
 *                      1.对象结构变化很困难
 *                      2.破坏封装
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        Element elA = new ElementA();
        Element elB = new ElementB();
        os.addElement(elA);
        os.addElement(elB);
        Visitor visitor1 = new ConcreteVisitor1();
        os.handlerRequest(visitor1);
    }
}
