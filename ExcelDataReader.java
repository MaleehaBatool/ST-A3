package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read test data from Excel (.xlsx) files.
 */
public class ExcelDataReader {

    /**
     * Reads integer triplet data [a, b, expected] from a named sheet in an Excel file.
     */
    public static List<int[]> readMathData(String filePath, String sheetName) throws Exception {
        List<int[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return data;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                int a = (int) row.getCell(0).getNumericCellValue();
                int b = (int) row.getCell(1).getNumericCellValue();
                int expected = (int) row.getCell(2).getNumericCellValue();
                data.add(new int[]{a, b, expected});
            }
        }
        return data;
    }

    /**
     * Creates a sample Excel file with test data sheets (Valid, Invalid, Edge, Stress).
     * Run this once to generate the Excel file for data-driven tests.
     */
    public static void createSampleExcelFile(String outputPath) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            String[] sheets = {"Valid", "Invalid", "Edge", "Stress"};
            int[][][] sheetData = {
                // Valid
                {{1,2,3},{10,20,30},{100,200,300},{0,5,5},{50,50,100},{5,5,10},{7,3,10},{15,5,20}},
                // Invalid (wrong expected)
                {{1,2,99},{5,5,0},{10,10,15},{3,4,0},{100,1,0},{7,7,1},{20,30,10},{9,1,5}},
                // Edge
                {{0,0,0},{-1,1,0},{Integer.MAX_VALUE,0,Integer.MAX_VALUE},{-100,100,0},{1,-1,0},{0,1,1},{-50,50,0},{0,-1,-1}},
                // Stress
                {{1000,2000,3000},{9999,1,10000},{5000,5000,10000},{100000,200000,300000},{999,1,1000},{10000,10000,20000},{50000,50000,100000},{99999,1,100000}}
            };

            for (int s = 0; s < sheets.length; s++) {
                Sheet sheet = workbook.createSheet(sheets[s]);
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("a");
                header.createCell(1).setCellValue("b");
                header.createCell(2).setCellValue("expected");
                for (int r = 0; r < sheetData[s].length; r++) {
                    Row row = sheet.createRow(r + 1);
                    row.createCell(0).setCellValue(sheetData[s][r][0]);
                    row.createCell(1).setCellValue(sheetData[s][r][1]);
                    row.createCell(2).setCellValue(sheetData[s][r][2]);
                }
            }
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        createSampleExcelFile("src/test/resources/data/math_test_data.xlsx");
        System.out.println("Excel test data file created successfully.");
    }
}
