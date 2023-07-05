package com.ds.netty.chapter02netty.d8chat.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/7/5
 * @description
 */
public class UserServiceMemoryImpl implements UserService {

    private final Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    @Override
    public boolean login(String username, String password) {
        final String pass = allUserMap.get(username);
        if (pass == null) {
            return false;
        }

        return pass.equals(password);
    }

}
