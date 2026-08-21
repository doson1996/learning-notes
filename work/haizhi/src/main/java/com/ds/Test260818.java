package com.ds;

/**
 * @author ds
 * @date 2026/8/18
 * @description
 */
public class Test260818 {
    public static void main(String[] args) {
        String str = "客户名称\n" +
                "法定代表\n" +
                "申请业务模式\n" +
                "最高授信额度\n" +
                "最高授信余额\n" +
                "当前本行低风险业务余额\n" +
                "当前本行敞口授信余额\n" +
                "申请业务品种\n" +
                "申请项下授信业务期限\n" +
                "申请敞口金额\n" +
                "利率浮动比例\n" +
                "保证金比例\n" +
                "授信用途\n" +
                "本行信用评级\n" +
                "担保方式\n" +
                "评估价值\n" +
                "认定价值\n" +
                "所属行业\n" +
                "营业收入\n" +
                "客户国际划型认定\n" +
                "从业人数\n" +
                "发生方式\n" +
                "该客户是否为本行关联方";

        String[] split = str.split("\n");
        for (String s : split) {
            System.out.println("map.put(\"" + s + "\", null);");
        }

    }
}
