package com.ds.dp.memento.simple;

/**
 * @Author ds
 * @Date 2021/4/2 10:27
 * @Description 负责保存备忘录的对象
 */
public class Caretaker {

    private Memento memento;

    /**
     * 保存备忘录对象
     * @param memento
     */
    public void saveMemento(Memento memento){
        this.memento = memento;
    }

    /**
     * 获取被保存的备忘录对象
     * @return
     */
    public Memento retriveMemento(){
        return this.memento;
    }

}
