package com.ds.basic.base;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2025/4/30
 * @description
 */
public class Dmo07AmountConverter {
    // 定义单位到“万元”的转换倍数
    private static final Map<String, BigDecimal> UNIT_TO_WAN = new HashMap<>();

    static {
        UNIT_TO_WAN.put("元", new BigDecimal("0.0001"));
        UNIT_TO_WAN.put("十元", new BigDecimal("0.001"));
        UNIT_TO_WAN.put("百元", new BigDecimal("0.01"));
        UNIT_TO_WAN.put("千元", new BigDecimal("0.1"));
        UNIT_TO_WAN.put("万元", BigDecimal.ONE);
        UNIT_TO_WAN.put("亿元", new BigDecimal("10000"));
    }

    // 示例测试
    public static void main(String[] args) {
        System.out.println(formatAsWan("12345.11", "元"));      // 1.23 万元
        System.out.println(formatAsWan("4000", "元"));      // 1.23 万元
        System.out.println(formatAsWan("5000", "元"));      // 1.23 万元
        System.out.println(formatAsWan("150", "千元"));      // 15.00 万元
        System.out.println(formatAsWan("2.5", "亿元"));      // 25000.00 万元
        System.out.println(formatAsWan("800", "百元"));      // 8.00 万元
        System.out.println(formatAsWan("9999999999", "元")); // 999999.9999 -> 1000000.00 万元（自动进位）
    }

    /**
     * 将指定单位的金额转换为“万元”
     *
     * @param amount 数值（字符串形式以避免精度问题）
     * @param unit   单位（如：元、千元、亿元）
     * @return 转换后的“万元”金额（保留两位小数，四舍五入）
     * @throws IllegalArgumentException 如果单位不支持
     */
    public static BigDecimal convertToWan(String amount, String unit) {
        BigDecimal factor = UNIT_TO_WAN.get(unit);
        if (factor == null) {
            throw new IllegalArgumentException("不支持的单位: " + unit);
        }
        BigDecimal amountDecimal = new BigDecimal(amount);
        return amountDecimal.multiply(factor).setScale(0, RoundingMode.HALF_UP);
    }

    /**
     * 格式化输出为保留两位小数的字符串
     */
    public static String formatAsWan(String amount, String unit) {
        BigDecimal result = convertToWan(amount, unit);
        return result.stripTrailingZeros().toPlainString() + " 万元";
    }


}
