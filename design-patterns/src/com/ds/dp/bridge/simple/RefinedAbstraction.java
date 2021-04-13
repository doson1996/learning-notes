package com.ds.dp.bridge.simple;

/**
 * @Author ds
 * @Date 2021/4/13 16:02
 * @Description
 */
public class RefinedAbstraction extends Abstraction{

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
       System.out.println("扩展方法");
       super.operation();
    }
}
