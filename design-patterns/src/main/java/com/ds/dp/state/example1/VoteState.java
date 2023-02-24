package com.ds.dp.state.example1;

/**
 * @Author ds
 * @Date 2021/4/1 10:26
 * @Description
 */
public interface VoteState {

    /**
     * 处理状态对应的行为
     * @param user
     * @param voteItem
     * @param voteManager
     */
    void vote(String user, String voteItem, VoteManager voteManager);
}
