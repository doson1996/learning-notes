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
public abstract class AbstractResponseMessage extends Message {
    private boolean success;
    private String reason;

    public AbstractResponseMessage() {
    }

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }
}
