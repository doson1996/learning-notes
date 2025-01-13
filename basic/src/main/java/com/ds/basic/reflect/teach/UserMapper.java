package com.ds.basic.reflect.teach;

/**
 * @author ds
 * @date 2024/11/13 21:39
 */
public class UserMapper implements Mapper {

    @Override
    public boolean insert(Object data) {
        if (data == null)
            return false;

        System.out.println("存入数据：" + data);
        return true;
    }

}
