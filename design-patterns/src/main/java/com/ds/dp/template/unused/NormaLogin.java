package com.ds.dp.template.unused;

/**
 * @Author ds
 * @Date 2021/3/26 17:08
 * @Description 普通用户登录（非设计模式）
 */
public class NormaLogin {

    /**
     * 示意方法，简单登录
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        //校验参数
        if (username == null || password == null) {
            return false;
        }

        User user = findByUsername(username);
        if (user != null) {
            if (username.equals(user.getUsername())
                    && password.equals(user.getPassword())) {
                System.out.println("用户登录成功---");
                return true;
            }

        }

        return false;
    }

    /**
     * 示意方法，根据用户名查找用户
     *
     * @param username
     * @return 用户信息
     */
    private User findByUsername(String username) {
        if ("no".equals(username)) {
            return null;
        }
        return new User(username, "123456");
    }


}
