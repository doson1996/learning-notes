package com.ds.netty.chapter02netty.d8chat.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
@Data
@ToString(callSuper = true)
public class PingMessage extends Message {
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
