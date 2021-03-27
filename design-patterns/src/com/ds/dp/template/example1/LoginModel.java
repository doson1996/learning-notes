package com.ds.dp.template.example1;

/**
 * @Author ds
 * @Date 2021/3/26 17:10
 * @Description
 */
public class LoginModel {

    private String username;

    private String password;

    public LoginModel(){}

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
