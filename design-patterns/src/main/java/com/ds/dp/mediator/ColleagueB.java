package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 14:55
 * @Description
 */
public class ColleagueB extends Colleague {

    public ColleagueB(Mediator mediator) {
        super(mediator);
    }

    /**
     * 示意方法
     */
    public void doSomething() {
        getMediator().change(this);
    }
}
