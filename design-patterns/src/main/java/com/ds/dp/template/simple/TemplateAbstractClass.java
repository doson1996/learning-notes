package com.ds.dp.template.simple;

/**
 * @Author ds
 * @Date 2021/3/26 17:34
 * @Description
 */
public abstract class TemplateAbstractClass {

    /**
     * 示意方法1，示意做某些事
     */
    public abstract void doSth1();

    /**
     * 示意方法2，示意做某些事
     */
    public abstract void doSth2();

    /**
     * 模板方法
     */
    public void template(){
        doSth1();
        doSth2();
    }
}
