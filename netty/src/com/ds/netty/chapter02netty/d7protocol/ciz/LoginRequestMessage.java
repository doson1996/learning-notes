package com.ds.netty.chapter02netty.d7protocol.ciz;

/**
 * @author ds
 * @date 2023/7/3
 * @description
 */
public class LoginRequestMessage extends AbstractMessage {

    private String name;

    private String password;

    public LoginRequestMessage(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public int getType() {
        return LOGIN_REQUEST;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestMessage{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
