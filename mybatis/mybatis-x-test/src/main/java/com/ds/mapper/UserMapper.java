package com.ds.mapper;

import com.ds.entity.User;
import com.ds.mybatisx.spring.annotion.Mapperx;

import java.util.List;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
@Mapperx
public interface UserMapper {

    List<User> selectAll();

    List<User> selectList(User user);

    User findUser(User user);

}
