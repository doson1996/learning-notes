package com.ds.dp.iterator.simple;

/**
 * @Author ds
 * @Date 2021/3/24 10:00
 * @Description 具体的迭代器实现对象
 */
public class ConcreteIterator implements Iterator{

    /**
     * 持有被迭代的具体的聚合对象
     */
    private ConcreteAggregate aggregate;

    /**
     * 内部所有，记录当前迭代到的索引位置
     */
    private int index = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < aggregate.size()){
            index++;
        }
    }

    @Override
    public boolean isDone() {
        if(index == aggregate.size()){
            return true;
        }
        return false;
    }

    @Override
    public Object currentItem() {
        return this.aggregate.get(index);
    }
}
