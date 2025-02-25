package com.ds.dp.visitor.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/4/14 14:01
 * @Description
 */
public class ObjectStructure {

    public List<Element> list = new ArrayList<>();

    /**
     * 示意方法，提供给客户端操作的高层接口
     *
     * @param visitor
     */
    public void handlerRequest(Visitor visitor) {
        for (Element element : list) {
            element.accept(visitor);
        }
    }

    /**
     * 示意方法，向对象结构中添加元素
     *
     * @param element
     */
    public void addElement(Element element) {
        list.add(element);
    }
}
