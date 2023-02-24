package com.ds.dp.state.example;

/**
 * @Author ds
 * @Date 2021/4/1 10:32
 * @Description
 */
public class NormalVoteState implements VoteState{

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //正常投票
        System.out.println("投票成功");
        voteManager.getVoteMap().put(user,voteItem);
    }
}
