package com.ds.dp.composite.unused;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/24 15:43
 * @Description 组合对象，可以包含其他组合对象和叶子对象
 */
public class Composite {

    /**
     * 其他组合对象
     */
    private List<Composite> childComposite = new ArrayList<>();

    /**
     * 叶子对象
     */
    private List<Leaf> childLeaf = new ArrayList<>();

    private String name;

    public Composite(String name) {
        this.name = name;
    }

    /**
     * 添加其他组合对象
     * @param composite
     */
    public void addChildComposite(Composite composite){
        childComposite.add(composite);
    }

    /**
     * 向组合对象添加叶子对象
     * @param leaf
     */
    public void addChildLeaf(Leaf leaf){
        childLeaf.add(leaf);
    }

    /**
     * 输出组合对象自身的结构
     * @param preStr
     */
    public void printStruct(String preStr){
        System.out.println(preStr + "-" + this.name);
        preStr = " ";

        //输出自己包含的叶子对象
        for (Leaf leaf : childLeaf) {
            leaf.printStruct(preStr);
        }

        //输出当前对象的子对象
        for (Composite composite : childComposite) {
            composite.printStruct(preStr);
        }
    }
}
