package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 14:40
 * @Description 同事类的抽象父类
 */
public abstract class Colleague {

    /**
     * 持有中介者对象，每一个同事都知道他的中介者类
     */
    private Mediator mediator;

    /**
     * 构造方法，传入中介者对象
     * @param mediator
     */
    public Colleague(Mediator mediator){
        this.mediator = mediator;
    }

    /**
     * 获取中介者对象
     * @return
     */
    public Mediator getMediator(){
       return mediator;
    }
}
