package com.ds.dp.decorator.simple;

/**
 * @Author ds
 * @Date 2021/4/8 11:29
 * @Description
 */
public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private String addState;

    public String getAddState() {
        return addState;
    }

    public void setAddState(String addState) {
        this.addState = addState;
    }

    @Override
    public void operation() {

        //调用父类的方法，可以在调用前后执行一些附件动作
        //在这里处理的时候，可以使用添加的状态
        super.operation();
    }
}
