package com.ds.dp.state.example;

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

    private VoteState state;

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
     * @return
     */
    public Map<String, String> getVoteMap() {
        return voteMap;
    }

    public Set<String> getBlacklist() {
        return blacklist;
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

        if (count == 1) {

            state = new NormalVoteState();
        } else if (count > 1 && count < 5) {

            state = new RepeatVoteState();
        } else if (count >= 5 && count < 8) {

            state = new SpiteVoteState();
        } else if (count >= 8) {

            state = new BlackVoteState();
        }

        state.vote(user, voteItem, this);
    }

}
