package com.ds.dp.proxy;

/**
 * @Author ds
 * @Date 2021/3/16 11:41
 * @Description
 */
public interface UserApi {

    int getUserId();

    void setUserId(int id);

    String getName();

    void setName(String name);

    int getDeptNo();

    void setDeptNo(int deptNo);

}
