package com.ds.dp.strategy.simple;

/**
 * @Author ds
 * @Date 2021/3/30 17:34
 * @Description 上下文对象，通常会持有一个具体的策略对象
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 上下文对客户端提供的操作接口，可以有参数和返回值
     *
     * @param arg
     * @return
     */
    public int contextInterface(int arg) {
        return strategy.algorithmInterface(arg);
    }

}
