package com.ds.basic.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/19 9:20
 * @Description 通过反射打破指定泛型
 */
public class Demo02Reflect {

    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();
        list.add("a");
        /**
         * 这里会提示需要的是String类型
         * Required type:
         *              String
         * Provided:
         *              int
         * Appends the specified element to the end of this list (optional operation).
         * Lists that support this operation may place limitations on what elements may be added to this list.
         * In particular, some lists will refuse to add null elements, and others will impose restrictions on the type of elements that may be added.
         * List classes should clearly specify in their documentation any restrictions on what elements may be added.
         */
        //list.add(1);

        /**
         * 通过反射打破指定泛型
         * getMethod("add",Object.class) 这里好像只能传Object.class
         * 如果传入其它的如 Integer.class 会报 NoSuchMethodException: java.util.List.add(java.lang.Integer)
         * 应该是由于java的泛型在编译的时候都会转为Object
         */
        List.class.getMethod("add",Object.class).invoke(list,1);

        System.out.println("list = " + list);
    }
}
