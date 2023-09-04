package com.ds.controller;

import com.ds.entity.User;
import com.ds.mapper.UserMapper;
import com.ds.mybatisx.starter.MyBatsixStarter;

import java.util.List;

/**
 * @author ds
 * @date 2023/8/21
 * @description
 */
public class UserController {
    public static void main(String[] args) throws Exception {
        UserMapper mapper = MyBatsixStarter.getMyBatsixStarter().getMapper(UserMapper.class, "mybatisx.xml");

        User user = new User();
        user.setId(3);
        user.setName("zs");
        List<User> userList = mapper.selectList(user);
        System.out.println("userList = " + userList);

        userList = mapper.selectAll();
        System.out.println("userList = " + userList);

        User user1 = mapper.findUser(user);
        System.out.println("user1 = " + user1);
    }
}
