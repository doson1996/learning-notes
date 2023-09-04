package com.ds.mapper;

import com.ds.entity.User;
import com.ds.mybatisx.annotation.Mapper;

import java.util.List;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
@Mapper
public interface UserMapper {

    List<User> selectAll();

    List<User> selectList(User user);

    User findUser(User user);

}
