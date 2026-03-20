package com.ds.springframework.chapter01.ylzr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ds
 * @date 2026/2/5
 * @description
 */
@Service
public class UserService {

    private UserDao userDao;

//    public UserService() {
//    }

//    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public UserService(UserDao userDao, UserDao userDao1) {
        this.userDao = userDao;
    }

    public void say() {
        System.out.println("UserService.userDao = " + userDao);
    }

}
