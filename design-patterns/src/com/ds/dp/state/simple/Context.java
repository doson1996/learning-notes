package com.ds.dp.state.simple;

/**
 * @Author ds
 * @Date 2021/4/1 10:22
 * @Description
 */
public class Context {

    private State state;

    public void setState(State state) {
        this.state = state;
    }

    /**
     *
     * @param parameter
     */
    public void request(String parameter) {
        state.handle(parameter);
    }
}
