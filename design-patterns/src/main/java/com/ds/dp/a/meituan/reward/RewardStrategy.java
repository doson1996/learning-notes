package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:35
 * @Description 抽象策略
 */
public abstract class RewardStrategy {

    /**
     * 奖励
     * @param userId
     */
    public abstract int reward(long userId);

    /**
     * 更新用户信息以及结算
     * @param userId
     * @param reward
     */
    public void insertRewardAndSettlement(long userId, int reward) {
      // 更新用户信息以及结算
        System.out.println("更新用户 " + userId + "信息以及结算 " + reward);
    }

}
