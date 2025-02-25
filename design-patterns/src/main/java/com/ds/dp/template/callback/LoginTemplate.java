package com.ds.dp.template.callback;

/**
 * @Author ds
 * @Date 2021/3/29 9:59
 * @Description
 */
public class LoginTemplate {

    /**
     * 登录
     *
     * @param loginModel
     * @param callback
     * @return
     */
    public final boolean login(LoginModel loginModel, LoginCallback callback) {

        LoginModel dbUser = callback.findLoginUser(loginModel.getUsername());
        if (dbUser != null) {
            String encryptPassword = callback.encryptPassword(loginModel.getPassword());
            loginModel.setPassword(encryptPassword);
            return callback.match(loginModel, dbUser, this);
        }

        return false;
    }


}
