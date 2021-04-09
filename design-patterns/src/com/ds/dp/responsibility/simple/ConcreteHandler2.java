package com.ds.dp.responsibility.simple;

/**
 * @Author ds
 * @Date 2021/4/9 10:23
 * @Description
 */
public class ConcreteHandler2 extends Handler{

    @Override
    public void handlerRequest() {

        /**
         * 根据条件判断是否属于自己处理的职责范围（可以从外部获取，这里只是示意）
         */
        boolean condition = true;

        if (condition) {
            //如果属于自己的职责范围，在这里处理请求
            System.out.println("concreteHandler2 handler request");
        } else {
            /**
             * 如果不属于自己的职责范围，那就判断是否有后继对象
             *  有 ->请求转发给后继对象处理
             * 没有->什么都不做，自然结束
             */
            if (handler != null) {
                handler.handlerRequest();
            }

        }

    }
}
