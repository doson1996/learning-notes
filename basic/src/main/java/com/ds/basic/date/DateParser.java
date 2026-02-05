package com.ds.basic.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ds
 * @date 2026/2/2
 * @description
 */
public class DateParser {

    // 支持的日期格式列表
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    );

    /**
     * 将字符串转换为 LocalDate，兼容多种格式
     *
     * @param dateStr 输入的日期字符串
     * @return 解析后的 LocalDate 对象
     * @throws IllegalArgumentException 如果无法解析日期
     */
    public static LocalDate parseDate(String dateStr) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
                // 忽略当前格式，继续尝试下一个
            }
        }
        throw new IllegalArgumentException("无法解析日期: " + dateStr);
    }

    public static void main(String[] args) {
        // 成立日期
        LocalDate establishmentDate = parseDate("2021-11-20");

        LocalDate baseDate = establishmentDate.plusYears(2);
        System.out.println("baseDate = " + baseDate);
        // 创建日期
        LocalDate creationDate = parseDate("2023-10-20"); // 可以修改为其他日期进行测试

        int i = creationDate.compareTo(baseDate);
        if (i > 0) {
            System.out.println("准入通过");
        } else {
            System.out.println("准入不通过");
        }

        // 计算年份差
//        long yearsBetween = ChronoUnit.YEARS.between(establishmentDate, creationDate);
//
//        // 判断是否超过两年
//        if (yearsBetween > 2) {
//            System.out.println("准入通过");
//        } else {
//            System.out.println("准入不通过");
//        }
    }
}