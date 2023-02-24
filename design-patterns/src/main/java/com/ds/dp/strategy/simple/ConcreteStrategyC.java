package com.ds.dp.strategy.simple;

/**
 * @Author ds
 * @Date 2021/3/30 17:33
 * @Description
 */
public class ConcreteStrategyC implements Strategy{

    @Override
    public int algorithmInterface(int arg) {
        return arg * 30;
    }
}
