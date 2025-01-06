package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class processes Excel files downloaded from Amazon Seller Central.
 * Specifically, it works with reports from the 'Berichtsrepository' section, containing transaction data
 * for a specified date range. The file must be in .xlsx format.
 *
 * Note: It is recommended to rename the file after downloading to include the date range or type of data
 * for easier identification, as there may be multiple similar files.
 */
public class IncomeReportMaker {

    private final List<Map<String, String>> data = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public IncomeReportMaker(String filePath, int headerRowIndex) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Erstes Blatt

            // Überschriften lesen
            Row headerRow = sheet.getRow(headerRowIndex);
            if (headerRow == null) {
                throw new IllegalArgumentException("Überschriften nicht in der angegebenen Zeile gefunden: " + headerRowIndex);
            }

            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Daten lesen
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
            if (dateStr.isEmpty()) continue; // Leere Datumswerte überspringen

            try {
                Date date = dateFormat.parse(dateStr);
                if (!date.before(startDate) && !date.after(endDate)) {
                    filteredData.add(row);
                }
            } catch (ParseException e) {
                // Reihen mit ungültigen Datumswerten überspringen
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
