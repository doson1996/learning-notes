package com.ds.dp.state.example;

/**
 * @Author ds
 * @Date 2021/4/1 10:35
 * @Description
 */
public class BlackVoteState implements VoteState{

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //加入黑名单
        System.out.println("已被加入黑名单");
        voteManager.getBlacklist().add(user);
    }
}
