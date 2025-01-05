package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;



public class SalesChannelActivityCalculator {

    public static void main(String[] args) {
        // Путь к файлу
        String desktopPath = "/Users/GiorUg/Desktop/Desktop PC bis 2023";
        String inputFileName = "7-12.24AmazonUmsaetze.xlsx";
        String inputFilePath = desktopPath + File.separator + inputFileName;

        // Названия колонок
        String salesChannelColumn = "SALES_CHANNEL"; // Имя колонки с каналами продаж
        String totalActivityColumn = "TOTAL_ACTIVITY_VALUE_AMT_VAT_INCL"; // Имя колонки с общими значениями

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист
            Row headerRow = sheet.getRow(0);

            // Найти индексы колонок
            int salesChannelIndex = findColumnIndex(headerRow, salesChannelColumn);
            int totalActivityIndex = findColumnIndex(headerRow, totalActivityColumn);

            if (salesChannelIndex == -1 || totalActivityIndex == -1) {
                System.err.println("Не найдены нужные колонки.");
                return;
            }

            // Подсчеты
            Map<String, double[]> results = new HashMap<>(); // "MFN" -> [сумма+, сумма-, общая]

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                String salesChannel = getCellValue(row.getCell(salesChannelIndex));
                String totalActivityValueStr = getCellValue(row.getCell(totalActivityIndex));

                if (salesChannel == null || totalActivityValueStr == null || salesChannel.isEmpty()) continue;

                totalActivityValueStr = totalActivityValueStr.replace(",", ".");
                double totalActivityValue;
                try {
                    totalActivityValue = Double.parseDouble(totalActivityValueStr);
                } catch (NumberFormatException e) {
                    continue; // Пропускаем некорректные значения
                }

                results.putIfAbsent(salesChannel, new double[3]);

                if (totalActivityValue > 0) {
                    results.get(salesChannel)[0] += totalActivityValue; // Сумма плюсов
                } else {
                    results.get(salesChannel)[1] += totalActivityValue; // Сумма минусов
                }
                results.get(salesChannel)[2] += totalActivityValue; // Общая сумма
            }

            // Вывод результатов
            results.forEach((key, values) -> {
                System.out.printf("Категория: %s%n", key);
                System.out.printf("Сумма положительных: %.2f%n", values[0]);
                System.out.printf("Сумма отрицательных: %.2f%n", values[1]);
                System.out.printf("Общая сумма: %.2f%n", values[2]);
                System.out.println("-----------------------------------");
            });

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static int findColumnIndex(Row headerRow, String columnName) {
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return null;
        }
    }
}
