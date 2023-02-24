package com.ds.jvm.classloader;

import sun.misc.Resource;

import java.io.*;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/4/19 22:34
 * @Description
 */
public class MyClassLoader extends ClassLoader{

    private String name;

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader(MyClassLoader.class.getClassLoader(),"A");
        Class<?> clazz = classLoader.loadClass("com.ds.jvm.classloader.A", false);
        System.out.println(classLoader.getClass().getClassLoader());
    }
    protected MyClassLoader(ClassLoader p, String name) {
        super(p);
        this.name = name;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            System.out.println(11);
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        Class<?> result = null;

        System.out.println("findClass---");
        try {
            File jclass = new File("D:\\code\\learning-notes\\target\\production\\jvm\\com\\ds\\jvm\\classloader\\A.class");
            InputStream fs =  new FileInputStream(jclass);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            int r = 0;
            while ((r = fs.read()) != -1){
                bs.write(r);
            }

            byte[] bytes = bs.toByteArray();
            result = this.defineClass(name, bytes, 0, bytes.length);

        } catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
}
