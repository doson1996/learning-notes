package com.ds.basic.dynamicproxy.dlcglib.d1;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/15
 * @description
 */
public class Proxy extends Target {

    private MethodInterceptor methodInterceptor;

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, m0, null, m01);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, m1, new Object[]{i}, m11);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(String s) {
        try {
            methodInterceptor.intercept(this, m2, new Object[]{s}, m21);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSuper() {
        super.save();
    }

    public void saveSuper(int i) {
        super.save(i);
    }

    public void saveSuper(String s) {
        super.save(s);
    }

    private static final Method m0;
    private static final Method m1;
    private static final Method m2;

    private static final MethodProxy m01;
    private static final MethodProxy m11;
    private static final MethodProxy m21;

    static {
        try {
            m0 = Target.class.getMethod("save");
            m1 = Target.class.getMethod("save", int.class);
            m2 = Target.class.getMethod("save", String.class);

            m01 = MethodProxy.create(Target.class, Proxy.class, "()V", "save", "saveSuper");
            m11 = MethodProxy.create(Target.class, Proxy.class, "(I)V", "save", "saveSuper");
            m21 = MethodProxy.create(Target.class, Proxy.class, "(Ljava/lang/String;)V", "save", "saveSuper");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
