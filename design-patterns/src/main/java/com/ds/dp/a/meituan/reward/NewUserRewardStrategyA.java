package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:37
 * @Description 新用户返奖具体策略A
 */
public class NewUserRewardStrategyA extends RewardStrategy {

    @Override
    public int reward(long userId) {
        //具体的计算逻辑
        System.out.println("新用户返奖计算逻辑");
        return 10;
    }

}
