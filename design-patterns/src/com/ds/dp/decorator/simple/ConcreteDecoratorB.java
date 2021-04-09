package com.ds.dp.decorator.simple;

/**
 * @Author ds
 * @Date 2021/4/8 11:29
 * @Description
 */
public class ConcreteDecoratorB extends Decorator{

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {

        //调用父类的方法，可以在调用前后执行一些附件动作
        //在这里处理的时候，可以使用添加的状态
        super.operation();
        addBehavior();
    }

    public void addBehavior(){
        //需要添加的职责实现
        System.out.println("需要添加的职责实现");
    }
}
