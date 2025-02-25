package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:40
 * @Description 策略抽象工厂
 */
public abstract class StrategyFactory<T> {

    /**
     * 生成策略
     *
     * @param c
     * @return
     */
    abstract RewardStrategy createStrategy(Class<T> c);

}
