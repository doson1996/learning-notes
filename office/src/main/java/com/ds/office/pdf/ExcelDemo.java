package com.ds.office.pdf;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ds
 * @date 2025/4/9
 * @description
 */
public class ExcelDemo {
    public static void main(String[] args) {
        // 定义Excel文件的路径
        String xlsDest = "/Users/ds/IdeaProjects/learning-notes/office/src/main/resources/output.xls";
        String xlsxDest = "/Users/ds/IdeaProjects/learning-notes/office/src/main/resources/output.xlsx";

        // 创建HSSFWorkbook（.xls格式）
        Workbook xlsWorkbook = new HSSFWorkbook();
        createExcelFile(xlsWorkbook, xlsDest);

        // 创建XSSFWorkbook（.xlsx格式）
        Workbook xlsxWorkbook = new XSSFWorkbook();
        createExcelFile(xlsxWorkbook, xlsxDest);

        System.out.println("Excel files created successfully.");
    }

    private static void createExcelFile(Workbook workbook, String dest) {
        // 创建一个工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        // 创建一行
        Row row = sheet.createRow(0);

        // 创建一个单元格
        Cell cell = row.createCell(0);

        // 设置单元格的值，包含括号
        cell.setCellValue("Hello (World)!");

        // 设置字体颜色
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

        // 添加封面内容
        addCoverPage(sheet, workbook);

        try (FileOutputStream fileOut = new FileOutputStream(dest)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭工作簿
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addCoverPage(Sheet sheet, Workbook workbook) {
        // 创建一行
        Row titleRow = sheet.createRow(1);

        // 创建一个单元格
        Cell titleCell = titleRow.createCell(0);

        // 设置单元格的值，包含括号
        titleCell.setCellValue("(Title)");

        // 设置字体颜色
        CellStyle titleCellStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setColor(IndexedColors.BLUE.getIndex());
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 24);
        titleCellStyle.setFont(titleFont);
        titleCell.setCellStyle(titleCellStyle);

        // 创建一行
        Row subtitleRow = sheet.createRow(2);

        // 创建一个单元格
        Cell subtitleCell = subtitleRow.createCell(0);

        // 设置单元格的值，包含括号
        subtitleCell.setCellValue("这是一个(示例)报告");

        // 设置字体颜色
        CellStyle subtitleCellStyle = workbook.createCellStyle();
        Font subtitleFont = workbook.createFont();
        subtitleFont.setColor(IndexedColors.GREEN.getIndex());
        subtitleFont.setFontHeightInPoints((short) 18);
        subtitleCellStyle.setFont(subtitleFont);
        subtitleCell.setCellStyle(subtitleCellStyle);

        // 创建一行
        Row dateRow = sheet.createRow(3);

        // 创建一个单元格
        Cell dateCell = dateRow.createCell(0);

        // 设置单元格的值，包含括号
        dateCell.setCellValue("日期: (2023-10-01)");

        // 设置字体颜色
        CellStyle dateCellStyle = workbook.createCellStyle();
        Font dateFont = workbook.createFont();
        dateFont.setColor(IndexedColors.BLACK.getIndex());
        dateFont.setFontHeightInPoints((short) 14);
        dateCellStyle.setFont(dateFont);
        dateCell.setCellStyle(dateCellStyle);
    }
}