package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelDataProcessor {
    private final List<Map<String, String>> data = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ExcelDataProcessor(String filePath, int headerRowIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист

            // Чтение заголовков
            Row headerRow = sheet.getRow(headerRowIndex);
            if (headerRow == null) {
                throw new IllegalArgumentException("Заголовки не найдены на указанной строке: " + headerRowIndex);
            }

            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Чтение данных
            for (int rowNum = headerRowIndex + 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int colNum = 0; colNum < headers.size(); colNum++) {
                    Cell cell = row.getCell(colNum);
                    rowData.put(headers.get(colNum), cell != null ? cell.toString().trim() : "");
                }
                data.add(rowData);
            }
        }
    }

    public List<Map<String, String>> filterByDate(String dateColumn, String startDateStr, String endDateStr) throws ParseException {
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        List<Map<String, String>> filteredData = new ArrayList<>();
        for (Map<String, String> row : data) {
            String dateStr = row.getOrDefault(dateColumn, "");
            try {
                Date date = dateFormat.parse(dateStr);
                if (!date.before(startDate) && !date.after(endDate)) {
                    filteredData.add(row);
                }
            } catch (ParseException e) {
                // Пропускаем строки с некорректной датой
            }
        }
        return filteredData;
    }

    public List<Map<String, String>> filterByColumn(String column, Set<String> values) {
        List<Map<String, String>> filteredData = new ArrayList<>();
        for (Map<String, String> row : data) {
            String cellValue = row.getOrDefault(column, "");
            if (values.contains(cellValue)) {
                filteredData.add(row);
            }
        }
        return filteredData;
    }

    public List<String> getUniqueValues(String column) {
        Set<String> uniqueValues = new HashSet<>();
        for (Map<String, String> row : data) {
            uniqueValues.add(row.getOrDefault(column, ""));
        }
        return new ArrayList<>(uniqueValues);
    }
}
