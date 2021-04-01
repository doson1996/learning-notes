package com.ds.dp.state.example;

/**
 * @Author ds
 * @Date 2021/4/1 10:35
 * @Description
 */
public class SpiteVoteState implements VoteState{

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //恶意投票
        System.out.println("恶意刷票，取消投票");
        voteManager.getVoteMap().remove(user);
    }
}
