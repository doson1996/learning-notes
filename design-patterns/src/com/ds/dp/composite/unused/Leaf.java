package com.ds.dp.composite.unused;

/**
 * @Author ds
 * @Date 2021/3/24 15:39
 * @Description 叶子对象
 */
public class Leaf {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    /**
     * 输出叶子对象的结构，
     * @param preStr 前缀，层级拼接
     */
    public void printStruct(String preStr){
        System.out.println(preStr + "-" + name);
    }
}
