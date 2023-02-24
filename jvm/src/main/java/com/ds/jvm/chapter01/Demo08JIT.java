package com.ds.jvm.chapter01;

/**
 * @Author ds
 * @Date 2021/5/24 19:31
 * @Description JIT编译器
 *              解释器   启动快，运行慢
 *              编译器   启动慢，运行快 （热点代码探测，）
 */
public class Demo08JIT {

    public static void main(String[] args) {
        /**
         * 纯解释器 -Xint  11894ms
         * 纯编译器 -Xcomp 1120ms
         * 混合模式 -Xmixed 1029ms
         */
        long start = System.currentTimeMillis();
        add(10000000);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
    }

    public static void add(int count) {
        for (int i = 0; i < count; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                if (j % 2 == 0) {
                    sum = sum + j;
                }
            }

        }
    }
}
