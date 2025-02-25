package com.ds.dp.state.example1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author ds
 * @Date 2021/4/1 9:48
 * @Description 投票管理
 */
public class VoteManager {

    /**
     * 记录投票结果 Map<String,String> 对应 Map<用户名称,投票结果>
     */
    private Map<String, String> voteMap = new HashMap<>();

    /**
     * 记录用户投票次数 Map<String,String> 对应 Map<用户名称,投票次数>
     */
    private Map<String, Integer> voteCount = new HashMap<>();

    /**
     * 黑名单
     */
    private Set<String> blacklist = new HashSet<>();

    /**
     * 记录用户投票状态   Map<String,VoteState> 对应  Map<用户名称,投票状态>
     */
    private Map<String, VoteState> stateMap = new HashMap<>();

    /**
     * @return
     */
    public Map<String, String> getVoteMap() {
        return voteMap;
    }

    public Map<String, Integer> getVoteCount() {
        return voteCount;
    }

    public Set<String> getBlacklist() {
        return blacklist;
    }

    public Map<String, VoteState> getStateMap() {
        return stateMap;
    }

    /**
     * 投票
     *
     * @param user     投票人
     * @param voteItem 投票选项
     */
    public void vote(String user, String voteItem) {

        boolean contains = blacklist.contains(user);
        if (contains) {
            System.out.println("已在黑名单，投票失败");
            return;
        }

        Integer count = voteCount.get(user);

        if (count == null) {
            count = 0;
        }
        count = count + 1;
        voteCount.put(user, count);

        VoteState state = stateMap.get(user);
        //如果还没有投票状态，说明还没有投过票
        if (state == null) {
            state = new NormalVoteState();
        }

        state.vote(user, voteItem, this);
    }

}
