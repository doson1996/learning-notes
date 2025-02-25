package com.ds.dp.template.callback;

/**
 * @Author ds
 * @Date 2021/3/29 9:55
 * @Description
 */
public interface LoginCallback {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    LoginModel findLoginUser(String username);

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    String encryptPassword(String password);

    /**
     * 验证登录数据
     *
     * @param loginModel
     * @param dbModel
     * @param loginTemplate
     * @return
     */
    boolean match(LoginModel loginModel, LoginModel dbModel, LoginTemplate loginTemplate);

}
