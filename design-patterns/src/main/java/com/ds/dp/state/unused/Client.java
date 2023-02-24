package com.ds.dp.state.unused;

/**
 * @Author ds
 * @Date 2021/4/1 10:06
 * @Description 不使用状态模式
 */
public class Client {

    public static void main(String[] args) {

        VoteManager manager = new VoteManager();
        for (int i = 0; i < 10; i++) {
            manager.vote("zs","投票");
        }

    }
}
