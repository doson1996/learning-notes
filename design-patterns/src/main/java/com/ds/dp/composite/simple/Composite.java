package com.ds.dp.composite.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/24 15:43
 * @Description 组合对象，可以包含其他组合对象和叶子对象
 */
public class Composite extends Component{

    /**
     * 子组件对象
     */
    private List<Component> childComponents = null;

    

    @Override
    public void addChild(Component child) {
        if(childComponents == null){
            childComponents = new ArrayList<>();
        }
       childComponents.add(child);
    }

    @Override
    public void removeChild(Component child) {
        if(childComponents != null){
            childComponents.remove(child);
        }
    }

    @Override
    public Component getChild(int index) {
        Component child = null;
        if(childComponents != null){
            if(index < 0 || index >= childComponents.size()){
                throw new ArrayIndexOutOfBoundsException("index:" + index + " out of bounds");
            }
            child = childComponents.get(index);
        }
        return child;
    }

    @Override
    public void someOperation() {
        if(childComponents != null){
            for (Component c : childComponents) {
                c.someOperation();
            }

        }
    }
}
