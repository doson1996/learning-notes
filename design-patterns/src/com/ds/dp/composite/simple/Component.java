package com.ds.dp.composite.simple;

/**
 * @Author ds
 * @Date 2021/3/24 16:06
 * @Description 抽象的组件对象，为组合中的对象声明接口，实现接口的缺省行为
 */
public abstract class Component {

    /**
     * 示意方法，子组件可能有的功能方法
     */
    public abstract void someOperation();

    /**
     *  向组合对象中添加组件对象
     * @param child
     */
    public void addChild(Component child){
        throw new UnsupportedOperationException("not support addChild");
    }

    /**
     *  向组合对象中移除组件对象
     * @param child
     */
    public void removeChild(Component child){
        throw new UnsupportedOperationException("not support removeChild");
    }

    /**
     *
     * @param index
     * @return
     */
    public Component getChild(int index){
        throw new UnsupportedOperationException("not support getChild");
    }

}
