package com.ds.dp.state.example1;

/**
 * @Author ds
 * @Date 2021/4/1 10:32
 * @Description
 */
public class NormalVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //正常投票
        System.out.println("投票成功");
        voteManager.getVoteMap().put(user,voteItem);

        //投票成功后，维护下一个状态(重复投票)
        voteManager.getStateMap().put(user,new RepeatVoteState());

    }
}
