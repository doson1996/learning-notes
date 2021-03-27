package com.ds.dp.template.example1;


/**
 * @Author ds
 * @Date 2021/3/26 17:48
 * @Description
 */
public abstract class LoginTemplate {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public final boolean login(String username,String password){
        //校验参数
        if(username == null || password == null){
            return false;
        }

        password = encryptPassword(password);

        LoginModel user = findByUsername(username);
        if(user != null){
            if(username.equals(user.getUsername())
                    && password.equals(user.getPassword())){
                System.out.println("登录成功---");
                return true;
            }

        }

        return false;
    }

    /**
     * 示意方法，根据用户名查找用户
     * @param username
     * @return 用户信息
     */
    public abstract LoginModel findByUsername(String username);

    /**
     * 示意方法，加密密码
     * @param password
     * @return
     */
    public String encryptPassword(String password){
        System.out.println("普通用户普通加密");
        return password + "123";
    }

}
