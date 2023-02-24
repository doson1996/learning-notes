package com.ds.dp.visitor.simple;

/**
 * @Author ds
 * @Date 2021/4/14 13:52
 * @Description
 */
public class ElementA extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementA(this);
    }

    /**
     * 示例方法，表示元素已有的实现功能
     */
    public void operationA() {
        //已有的实现功能
        System.out.println("operationA");
    }
}
