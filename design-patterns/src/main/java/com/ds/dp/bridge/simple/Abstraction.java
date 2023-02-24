package com.ds.dp.bridge.simple;

/**
 * @Author ds
 * @Date 2021/4/13 15:54
 * @Description 定义抽象部分的接口
 */
public abstract class Abstraction {

    /**
     * 持有一个实现部分的对象
     */
    protected Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    /**
     * 实例操作，实现一定的功能，可能需要转调实现部分的具体实现方法
     */
    public void operation(){
        implementor.operationImpl();
    }
}
