package com.ds.controller;

import com.ds.entity.User;
import com.ds.mapper.UserMapper;
import com.ds.mybatisx.starter.MyBatsixStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ds
 * @date 2023/8/21
 * @description
 */
@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    public List<User> qryUser() {
        return userMapper.selectAll();
    }

}
