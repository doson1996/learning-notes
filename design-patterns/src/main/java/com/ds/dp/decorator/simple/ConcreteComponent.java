package com.ds.dp.decorator.simple;

/**
 * @Author ds
 * @Date 2021/4/8 11:24
 * @Description 具体实现组件对象接口的对象
 */
public class ConcreteComponent extends Component {

    @Override
    public void operation() {
        //相应的功能处理
        System.out.println("ConcreteComponent operation");
    }
}
