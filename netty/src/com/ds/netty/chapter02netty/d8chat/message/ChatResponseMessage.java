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
public class ChatResponseMessage extends AbstractResponseMessage {
    private String from;
    private String content;

    public ChatResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    public ChatResponseMessage(String from, String content) {
        this.from = from;
        this.content = content;
    }

    @Override
    public int getMessageType() {
        return ChatResponseMessage;
    }
}
