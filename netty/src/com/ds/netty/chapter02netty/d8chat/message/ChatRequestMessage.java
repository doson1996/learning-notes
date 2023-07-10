package com.ds.netty.chapter02netty.d8chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author ds
 * @date 2023/7/10
 * @description
 */
@Data
@ToString(callSuper = true)
public class ChatRequestMessage extends Message {
    private String content;
    private String to;
    private String from;

    public ChatRequestMessage() {
    }

    public ChatRequestMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public int getMessageType() {
        return ChatRequestMessage;
    }

    @Override
    public String toString() {
        return "ChatRequestMessage{" +
                "content='" + content + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
