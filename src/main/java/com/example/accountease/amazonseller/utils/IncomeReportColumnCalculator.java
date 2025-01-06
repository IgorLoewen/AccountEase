package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * This program calculates the positive and negative sums of numeric columns in an Excel file.
 * Specifically, it processes transaction data and sums up the values for specified numeric columns.
 *
 * Requirements:
 * - The Excel file should be in .xlsx format.
 * - Column headers are expected to be on the 8th row (index 7).
 *
 * Usage:
 * Update the file path to point to the target Excel file.
 * Run the program to calculate and print the sums of the specified columns.
 */
public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        // Path to the Excel file
        String filePath = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // First sheet

            // Headers are located in the 8th row (index 7)
            Row headerRow = sheet.getRow(7);
            if (headerRow == null) {
                System.err.println("Überschriftenreihe in Zeile 8 nicht gefunden.");
                return;
            }

            // Reading headers
            LinkedHashMap<String, Integer> columnIndexMap = new LinkedHashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String columnName = headerRow.getCell(i).getStringCellValue().trim();
                columnIndexMap.put(columnName, i);
            }

            // List of columns with numeric values (excluding Postleitzahl)
            List<String> numericColumns = Arrays.asList(
                    "Umsätze", "Produktumsatzsteuer", "Gutschrift für Versandkosten",
                    "Steuer auf Versandgutschrift", "Gutschrift für Geschenkverpackung",
                    "Steuer auf Geschenkverpackungsgutschriften", "Rabatte aus Werbeaktionen",
                    "Steuer auf Aktionsrabatte", "Einbehaltenе Steuer auf Marketplace",
                    "Verkaufsgebühren", "Gebühren zu Versand durch Amazon", "Andere Transaktionsgebühren",
                    "Andere", "Gesamt"
            );

            // Storage for sums of each column
            Map<String, Double> positiveSums = new HashMap<>();
            Map<String, Double> negativeSums = new HashMap<>();

            for (String column : numericColumns) {
                positiveSums.put(column, 0.0);
                negativeSums.put(column, 0.0);
            }

            // Summing up values
            for (int rowNum = 8; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                for (String column : numericColumns) {
                    Integer columnIndex = columnIndexMap.get(column);
                    if (columnIndex == null) continue;

                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        try {
                            // Convert string to number (replace commas with dots)
                            double value = 0.0;
                            if (cell.getCellType() == CellType.STRING) {
                                value = Double.parseDouble(cell.getStringCellValue().replace(",", "."));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                value = cell.getNumericCellValue();
                            }

                            if (value > 0) {
                                positiveSums.put(column, positiveSums.get(column) + value);
                            } else if (value < 0) {
                                negativeSums.put(column, negativeSums.get(column) + value);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Fehler bei der Umwandlung des Werts in Spalte " + column + " in Zeile " + (rowNum + 1));
                        }
                    }
                }
            }

            // Output results
            System.out.println("Spaltensummen:");
            for (String column : numericColumns) {
                System.out.println("Spalte: " + column);
                System.out.println("  Summe der positiven Werte: " + positiveSums.get(column));
                System.out.println("  Summe der negativen Werte: " + negativeSums.get(column));
            }

        } catch (IOException e) {
            System.err.println("Fehler: " + e.getMessage());
        }
    }
}
