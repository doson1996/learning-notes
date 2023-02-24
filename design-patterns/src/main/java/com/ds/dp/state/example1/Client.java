package com.ds.dp.state.example1;


/**
 * @Author ds
 * @Date 2021/4/1 10:06
 * @Description 使用状态模式,通过上下文(VoteManager)来维护状态
 */
public class Client {

    public static void main(String[] args) {

        VoteManager manager = new VoteManager();
        for (int i = 0; i < 10; i++) {
            manager.vote("zs","投票");
        }

    }
}
