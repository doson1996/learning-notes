package com.ds.dp.template.example1;

import com.ds.dp.template.unused.Admin;

/**
 * @Author ds
 * @Date 2021/3/26 17:08
 * @Description 后台用户登录（非设计模式）
 */
public class AdminLogin extends LoginTemplate{

    /**
     * 示意方法，根据用户名查找用户
     * @param username
     * @return 用户信息
     */
    @Override
    public LoginModel findByUsername(String username){
        if ("no".equals(username)){
            return null;
        }
        return new LoginModel(username,encryptPassword("123456"));
    }

    /**
     * 示意方法，加密密码
     * @param password
     * @return
     */
    @Override
    public String encryptPassword(String password){
        System.out.println("后台用户使用md5加密");
        return password + "MD5";
    }


}
