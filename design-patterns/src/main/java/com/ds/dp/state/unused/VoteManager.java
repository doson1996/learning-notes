package com.ds.dp.state.unused;

import java.util.*;

/**
 * @Author ds
 * @Date 2021/4/1 9:48
 * @Description 投票管理
 */
public class VoteManager {

    /**
     * 记录投票结果 Map<String,String> 对应 Map<用户名称,投票结果>
     */
    private Map<String,String> voteMap = new HashMap<>();

    /**
     * 记录用户投票次数 Map<String,String> 对应 Map<用户名称,投票次数>
     */
    private Map<String,Integer> voteCount = new HashMap<>();

    /**
     * 黑名单
     */
    private Set<String> blacklist = new HashSet<>();

    /**
     * 投票
     * @param user      投票人
     * @param voteItem  投票选项
     */
    public void vote(String user,String voteItem){

        boolean contains = blacklist.contains(user);
        if (contains){

            System.out.println("已在黑名单，投票失败");
            return;
        }

        Integer count = voteCount.get(user);

        if(count == null){
            count = 0;
        }
        count = count + 1;
        voteCount.put(user,count);

        if(count == 1){

            System.out.println("正常投票,成功");
            voteMap.put(user,voteItem);
        } else if(count > 1 && count < 5){

            System.out.println("请不要重复投票");
        } else if (count >= 5 && count < 8){

            System.out.println("恶意刷票，取消投票");
            voteMap.remove(user);
        } else if (count >= 8){

            System.out.println("已加入黑名单");
            blacklist.add(user);
        }

    }

}
