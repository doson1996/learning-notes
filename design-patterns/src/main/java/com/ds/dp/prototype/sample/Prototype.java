package com.ds.dp.prototype.sample;

/**
 * @Author ds
 * @Date 2021/3/15 11:44
 * @Description 原型模式示例代码
 * 优点：
 * 1.对客户端隐藏具体的实现
 * 2.在运行时动态改变具体的实现类型
 * 缺点：
 * 每个原型子类都必须实现clone的操作
 */
public interface Prototype {

    /**
     * 克隆自身的方法
     *
     * @return
     */
    Prototype clone();
}
