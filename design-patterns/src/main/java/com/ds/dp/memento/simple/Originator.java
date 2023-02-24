package com.ds.dp.memento.simple;

/**
 * @Author ds
 * @Date 2021/4/2 10:16
 * @Description 原发对象
 */
public class Originator {

    /**
     * 示意，表示原发器状态
     */
    private String state;

    public Memento createMemento(){
        return new MementoImpl(state);
    }

    public void setMemento(Memento memento){
        MementoImpl memento1 = (MementoImpl) memento;
        this.state = memento1.getState();
    }

    /**
     * 真正的备忘录对象
     */
    private static class MementoImpl implements Memento{

        /**
         * 示意，需要保存的状态
         */
        private String state;

        public MementoImpl(String state) {
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

}
