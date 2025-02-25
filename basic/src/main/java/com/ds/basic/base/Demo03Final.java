package com.ds.basic.base;

/**
 * @author ds
 * @date 2021/8/24 22:32
 */
public class Demo03Final {

    public static final User ZS = new User("zs", 20);

    public static void main(String[] args) {
        /**
         * Cannot assign a value to final variable 'ZS'
         * 无法为最终变量“ZS”赋值
         */
        //  ZS = new User("ls", 21);
    }
}
