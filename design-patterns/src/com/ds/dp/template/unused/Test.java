package com.ds.dp.template.unused;

/**
 * @Author ds
 * @Date 2021/3/26 17:23
 * @Description 没有使用设计模式
 *                  问题：
 *                      1.重复的代码多
 *                      2.扩展不方便
 */
public class Test {

    public static void main(String[] args) {
        String username = "zhangsan";
        String password = "123456";

        NormaLogin normaLogin = new NormaLogin();
        normaLogin.login(username,password);

        AdminLogin adminLogin = new AdminLogin();
        adminLogin.login(username,password);
    }
}
