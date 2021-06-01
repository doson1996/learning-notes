package com.ds.jvm.chapter01;

/**
 * @author ds
 * @date 2021/6/1 23:08
 */
public class Demo14String {
    public static void main(String[] args) {
        /**
         * 创建了两个对象（如果常量池中没有ab）
         * new #2 <java/lang/String>
         * ldc #3 <ab>
         */
       // String str1 = new String("ab");

        /**
         * 创建了6个对象
         * 1.     new #2 <java/lang/StringBuilder>
         * 2. new String("a")   new #4 <java/lang/String>
         * 3. 常量池中的“a”      ldc #5 <a>
         * 4. new String("b")  new #4 <java/lang/String>
         * 5. 常量池中的“b”    ldc #8 <b>
         * 6. StringBuilder 的 toString方法 new String("ab") <注意：此时在常量池中并没有“ab”>  invokevirtual #9 <java/lang/StringBuilder.toString>
         */
        String str2 = new String("a") + new String("b");

    }
}
