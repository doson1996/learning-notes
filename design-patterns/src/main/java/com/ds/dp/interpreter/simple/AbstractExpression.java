package com.ds.dp.interpreter.simple;

/**
 * @Author ds
 * @Date 2021/4/7 10:06
 * @Description
 */
public abstract class AbstractExpression {

    /**
     * 解释的操作
     *
     * @param ctx
     */
    public abstract void interpret(Context ctx);
}
