package com.ds.dp.decorator.simple;

/**
 * @Author ds
 * @Date 2021/4/8 11:26
 * @Description 装饰器接口
 */
public abstract class Decorator extends Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        //转发请求给组件对象，可以在转发前后执行一些附件动作
        component.operation();
    }
}
