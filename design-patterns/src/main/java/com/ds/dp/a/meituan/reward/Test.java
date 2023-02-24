package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 16:08
 * @Description 策略 + 工厂模式
 */
public class Test {

    public static void main(String[] args) {

        InviteRewardImpl inviteReward = new InviteRewardImpl();
        inviteReward.sendReward(2);
    }
}
