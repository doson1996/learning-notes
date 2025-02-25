package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 14:45
 * @Description 具体的中介者实现
 */
public class ConcreatMediator implements Mediator {

    private ColleagueA colleagueA;

    private ColleagueB colleagueB;

    public void setColleagueA(ColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    @Override
    public void change(Colleague colleague) {
        //发生改变后，具体协调相应的同事对象来实现协作行为
    }
}
