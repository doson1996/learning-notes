package com.ds.dp.template.unused;

/**
 * @Author ds
 * @Date 2021/3/26 17:08
 * @Description 后台用户登录（非设计模式）
 */
public class AdminLogin {

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

        password = encryptPassword(password);

        Admin admin = findByUsername(username);
        if (admin != null) {
            if (username.equals(admin.getUsername())
                    && password.equals(admin.getPassword())) {
                System.out.println("管理员登录成功---");
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
    private Admin findByUsername(String username) {
        if ("no".equals(username)) {
            return null;
        }
        return new Admin(username, encryptPassword("123456"));
    }

    /**
     * 示意方法，加密密码
     *
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        return password + "123";
    }


}
