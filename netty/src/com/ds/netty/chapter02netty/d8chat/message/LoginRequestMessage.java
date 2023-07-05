package com.ds.netty.chapter02netty.d8chat.message;

import lombok.Data;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
@Data
public class LoginRequestMessage extends Message{
    private String username;
    private String password;

    public LoginRequestMessage() {
    }

    public LoginRequestMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}
