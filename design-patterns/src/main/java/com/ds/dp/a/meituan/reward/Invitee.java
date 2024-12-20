package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:55
 * @Description
 */
public class Invitee {

    private final int userType;

    public Invitee(int userType){
        this.userType = userType;
    }

    public int getUserType(){
        return this.userType;
    }

}
