package com.ds.dp.composite.example;

/**
 * @Author ds
 * @Date 2021/3/24 15:39
 * @Description 叶子对象
 */
public class Leaf extends Component {

    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr + "-" + name);
    }
}
