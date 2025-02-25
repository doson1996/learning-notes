package com.ds.dp.a.meituan.reward;

/**
 * @Author ds
 * @Date 2021/3/26 15:52
 * @Description
 */
public enum UserTypeEnum {
    /**
     * 新用户
     */
    NEW_USER(1),

    /**
     * 老用户
     */
    OLD_USER(2);

    private int type;

    UserTypeEnum(int type) {
        this.type = type;
    }

}
