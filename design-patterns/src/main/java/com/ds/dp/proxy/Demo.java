package com.ds.dp.proxy;

import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/16 9:50
 * @Description
 */
public class Demo {
    public static void main(String[] args) {
        /*UserManager userManager = new UserManager();
        List<User> list = userManager.getByDeptNo(10);
        System.out.println(list);*/

        UserManagerProxy proxy = new UserManagerProxy();
        List<UserApi> list = proxy.getByDeptNo(10);

        for (UserApi userApi : list) {
            System.out.println(userApi.getUserId() + "-" + userApi.getDeptNo());
        }

        for (UserApi userApi : list) {
            System.out.println(userApi.getUserId() + "-" + userApi.getName() + "-" + userApi.getDeptNo());
        }


    }
}
