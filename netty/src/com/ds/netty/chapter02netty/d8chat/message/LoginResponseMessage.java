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
public class LoginResponseMessage extends AbstractResponseMessage {
    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
