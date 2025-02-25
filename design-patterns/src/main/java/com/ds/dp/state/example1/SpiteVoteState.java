package com.ds.dp.state.example1;

/**
 * @Author ds
 * @Date 2021/4/1 10:35
 * @Description
 */
public class SpiteVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //恶意投票
        Integer count = voteManager.getVoteCount().get(user);
        System.out.println("恶意刷票，取消投票");
        voteManager.getVoteMap().remove(user);
        if (count >= 7) {
            voteManager.getStateMap().put(user, new BlackVoteState());
        }
    }
}
