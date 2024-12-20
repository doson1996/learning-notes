package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:51
 * @Description
 */
public class InviteRewardImpl {
    //返奖主流程
    public void sendReward(long userId) {
        //创建工厂
        FactorRewardStrategyFactory strategyFactory = FactorRewardStrategyFactory.getInstance();

        //根据用户id查询用户信息
        Invitee invitee = getInviteeByUserId(userId);

        //新用户返奖策略
        if (invitee.getUserType() == 1) {
            NewUserRewardStrategyA newUserReward = (NewUserRewardStrategyA) strategyFactory.createStrategy(NewUserRewardStrategyA.class);
            RewardContext rewardContext = new RewardContext(newUserReward);
            //执行返奖策略
            rewardContext.doStrategy(userId);
        }

        //老用户返奖策略
        if (invitee.getUserType() == 2) {
            OldUserRewardStrategyA oldUserReward = (OldUserRewardStrategyA) strategyFactory.createStrategy(OldUserRewardStrategyA.class);
            RewardContext rewardContext = new RewardContext(oldUserReward);
            //执行返奖策略
            rewardContext.doStrategy(userId);
        }
    }

    private Invitee getInviteeByUserId(long userId) {
        if (userId == 1L) {
            return new Invitee(1);
        }
        return new Invitee(2);
    }
}
