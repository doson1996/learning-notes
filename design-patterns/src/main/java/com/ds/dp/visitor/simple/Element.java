package com.ds.dp.visitor.simple;

/**
 * @Author ds
 * @Date 2021/4/14 13:53
 * @Description
 */
public abstract class Element {

    /**
     * 接受访问者的访问
     *
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
}
