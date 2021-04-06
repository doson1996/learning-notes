package com.ds.dp.flyweight.simple;

/**
 * @Author ds
 * @Date 2021/4/6 9:57
 * @Description 享元接口
 */
public interface Flyweight {

    /**
     * 示例操作，传入外部状态
     * @param extState
     */
    void operation(String extState);
}
