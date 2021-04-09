package com.ds.dp.builder;

/**
 * @Author ds
 * @Date 2021/3/15 10:06
 * @Description 生成器模式
 *              优点: 1.松散耦合
 *                   2.可以很容易的改变产品内部的表示
 *                   3.更好的复用性
 */
public class BuilderClient01 {

    public static void main(String[] args) {

        User zhangsan = User.builder().name("zhangsan").email("123@qq.com").build();
        System.out.println(zhangsan);

        User lisi = User.builder().name(null).build();
        System.out.println(lisi);
    }
}
