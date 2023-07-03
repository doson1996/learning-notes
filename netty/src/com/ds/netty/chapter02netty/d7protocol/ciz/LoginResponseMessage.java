package com.ds.netty.chapter02netty.d7protocol.ciz;

/**
 * @author ds
 * @date 2023/7/3
 * @description
 */
public class LoginResponseMessage extends AbstractMessage {
    @Override
    public int getType() {
        return LOGIN_RESPONSE;
    }
}
