package com.ds.dp.memento.simple;

/**
 * @Author ds
 * @Date 2021/4/2 9:57
 * @Description 备忘录模式 （本质：保存和恢复内部状态）
 */
public class Client {

    public static void main(String[] args) {

        Originator originator = new Originator();
        Memento memento = originator.createMemento();

        Caretaker caretaker = new Caretaker();
        caretaker.saveMemento(memento);

    }
}
