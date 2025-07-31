package com.cts.miniproject.frameworkutils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWrite {

    public static void writeToExcel(List<List<String>> tableData, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("EMI Table");
        int rowCount = 0;
        for (List<String> rowData : tableData) {
            Row row = sheet.createRow(rowCount++);
            int cellCount = 0;
            for (String cellValue : rowData) {
                Cell cell = row.createCell(cellCount++);
                cell.setCellValue(cellValue);
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Excel file written successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
