package com.ds.dp.iterator.simple;

/**
 * @Author ds
 * @Date 2021/3/24 9:48
 * @Description 具体的聚合对象，创建相应的迭代器
 */
public class ConcreteAggregate extends Aggregate{

    /**
     * 示意，表示聚合对象具体内容
     */
    private String[] ss = null;

    public ConcreteAggregate(String[] ss) {
        this.ss = ss;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     *
     * @param index
     * @return
     */
    public Object get(int index){
        Object item = null;
        if(index < ss.length){
            item = ss[index];
        }
        return item;
    }

    public int size(){
        return ss.length;
    }
}
