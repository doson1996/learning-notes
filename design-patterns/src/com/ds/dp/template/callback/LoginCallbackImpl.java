package com.ds.dp.template.callback;

/**
 * @Author ds
 * @Date 2021/3/29 10:14
 * @Description
 */
public class LoginCallbackImpl implements LoginCallback{

    @Override
    public LoginModel findLoginUser(String username) {
        return new LoginModel(username,"123456");
    }

    @Override
    public String encryptPassword(String password) {
        return password;
    }

    @Override
    public boolean match(LoginModel loginModel, LoginModel dbModel, LoginTemplate loginTemplate) {
        if(loginModel.getUsername().equals(dbModel.getUsername())
                && loginModel.getPassword().equals(dbModel.getPassword())){
            return true;
        }

        return false;
    }
}
