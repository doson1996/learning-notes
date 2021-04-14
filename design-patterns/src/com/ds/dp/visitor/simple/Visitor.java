package com.ds.dp.visitor.simple;/**
 * @Author  ds
 * @Date    2021/4/14 13:50
 * @Description
 */public interface Visitor {

    /**
     * 访问元素A
     * @param elementA
     */
     void visitElementA(ElementA elementA);

    /**
     * 访问元素A
     * @param elementB
     */
    void visitElementB(ElementB elementB);
}
