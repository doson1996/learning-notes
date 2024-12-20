package com.ds.dp.a.meituan.reward;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ds
 * @Date 2021/3/26 15:41
 * @Description 具体工厂
 */
public class FactorRewardStrategyFactory extends StrategyFactory {

    private static final FactorRewardStrategyFactory INSTANCE = new FactorRewardStrategyFactory();

    private FactorRewardStrategyFactory() {

    }

    public static FactorRewardStrategyFactory getInstance() {
        return INSTANCE;
    }

    private final ConcurrentHashMap<Class, RewardStrategy> strategies = new ConcurrentHashMap<>();

    @Override
    RewardStrategy createStrategy(Class c) {
        RewardStrategy rewardStrategy = strategies.get(c);
        if (rewardStrategy != null) {
            return rewardStrategy;
        }
        try {
            rewardStrategy = (RewardStrategy) Class.forName(c.getName()).newInstance();
            strategies.put(c, rewardStrategy);
        } catch (Exception e) {
            System.out.println("创建策略异常: " + e.getLocalizedMessage());
        }

        return rewardStrategy;
    }

}
