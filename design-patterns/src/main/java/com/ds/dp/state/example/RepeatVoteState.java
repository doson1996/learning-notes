package com.ds.dp.state.example;

/**
 * @Author ds
 * @Date 2021/4/1 10:34
 * @Description
 */
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //重复投票
        System.out.println("请不要重复投票");
    }
}
