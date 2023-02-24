package com.ds.dp.visitor.simple;

/**
 * @Author ds
 * @Date 2021/4/14 13:53
 * @Description
 */
public class ElementB extends Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementB(this);
    }

    /**
     * 示例方法，表示元素已有的实现功能
     */
    public void operationB() {
        //已有的实现功能
        System.out.println("operationB");
    }
}
