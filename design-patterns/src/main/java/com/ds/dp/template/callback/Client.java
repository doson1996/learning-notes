package com.ds.dp.template.callback;

/**
 * @Author ds
 * @Date 2021/3/29 10:18
 * @Description 回调实现模板方法模式
 */
public class Client {

    public static void main(String[] args) {

        LoginModel loginModel = new LoginModel("zs","123456");
        LoginTemplate loginTemplate = new LoginTemplate();
        boolean login = loginTemplate.login(loginModel, new LoginCallbackImpl());
        System.out.println(login);
    }
}
