package com.ds.dp.template.simple;

/**
 * @Author ds
 * @Date 2021/3/26 17:46
 * @Description 模板方法设计模式
 * 优点：
 * 1.实现代码复用
 * 缺点：
 * 1.算法骨架不容易升级
 */
public class Test {

    public static void main(String[] args) {
        TemplateAbstractClass template = new ConcreteClass();
        template.template();
    }
}
