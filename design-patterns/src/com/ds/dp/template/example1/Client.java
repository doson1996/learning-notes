package com.ds.dp.template.example1;

/**
 * @Author ds
 * @Date 2021/3/26 17:56
 * @Description 使用模板方法设计模式重写 {@link com.ds.dp.template.unused.Test}
 */
public class Client {

    public static void main(String[] args) {

        LoginModel user = new LoginModel("zs","123456");
        NormaLogin normaLogin = new NormaLogin();
        normaLogin.login(user.getUsername(),user.getPassword());

        LoginModel admin = new LoginModel("ls","123456");
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.login(admin.getUsername(),admin.getPassword());
    }
}
