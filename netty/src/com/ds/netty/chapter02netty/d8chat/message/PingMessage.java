package com.ds.netty.chapter02netty.d8chat.message;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
public class PingMessage extends Message {
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
