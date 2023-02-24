package com.ds.dp.visitor.simple;

/**
 * @Author ds
 * @Date 2021/4/14 13:57
 * @Description
 */
public class ConcreteVisitor1 implements Visitor {
    @Override
    public void visitElementA(ElementA elementA) {
        //把访问ElementA时，需要执行的功能实现在这里
        //可能需要访问元素已有的功能
        elementA.operationA();
    }

    @Override
    public void visitElementB(ElementB elementB) {
        //把访问ElementB时，需要执行的功能实现在这里
        //可能需要访问元素已有的功能
        elementB.operationB();
    }
}
