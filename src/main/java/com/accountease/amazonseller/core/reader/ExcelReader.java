package com.accountease.amazonseller.core.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    /**
     * Reads data from an Excel file and returns it as a list of maps.
     * Each map represents a row, where the keys are column names.
     *
     * @param filePath       Path to the Excel file.
     * @param headerRowIndex Index of the row containing column headers.
     * @return List of rows represented as maps.
     * @throws IOException If an error occurs while reading the file.
     */
    public static List<Map<String, String>> readExcel(String filePath, int headerRowIndex) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист
            Row headerRow = sheet.getRow(headerRowIndex);

            if (headerRow == null) {
                throw new IllegalArgumentException("Header row not found at index: " + headerRowIndex);
            }

            // Считываем заголовки колонок
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Считываем данные строк
            for (int rowNum = headerRowIndex + 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int colNum = 0; colNum < headers.size(); colNum++) {
                    Cell cell = row.getCell(colNum);
                    rowData.put(headers.get(colNum), cell != null ? cell.toString().trim() : "");
                }
                rows.add(rowData);
            }
        }

        return rows;
    }
}
