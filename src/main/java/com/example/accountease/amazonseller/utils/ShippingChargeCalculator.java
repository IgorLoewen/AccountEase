package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ShippingChargeCalculator {

    public static void main(String[] args) {
        // Путь к файлу
        String desktopPath = "/Users/GiorUg/Desktop/Desktop PC bis 2023";
        String inputFileName = "7-12.24AmazonUmsaetze.xlsx";
        String inputFilePath = desktopPath + File.separator + inputFileName;

        // Названия колонок
        String salesChannelColumn = "SALES_CHANNEL"; // Имя колонки с каналами продаж
        String totalShipChargeColumn = "TOTAL_SHIP_CHARGE_AMT_VAT_INCL"; // Имя колонки с суммами доставки

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист
            Row headerRow = sheet.getRow(0);

            // Найти индексы колонок
            int salesChannelIndex = findColumnIndex(headerRow, salesChannelColumn);
            int totalShipChargeIndex = findColumnIndex(headerRow, totalShipChargeColumn);

            if (salesChannelIndex == -1 || totalShipChargeIndex == -1) {
                System.err.println("Не найдены нужные колонки.");
                return;
            }

            // Подсчеты
            Map<String, double[]> results = new HashMap<>(); // "MFN" -> [сумма+, сумма-, общая]

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                String salesChannel = getCellValue(row.getCell(salesChannelIndex));
                String totalShipChargeStr = getCellValue(row.getCell(totalShipChargeIndex));

                if (salesChannel == null || totalShipChargeStr == null || salesChannel.isEmpty()) continue;

                totalShipChargeStr = totalShipChargeStr.replace(",", ".");
                double totalShipCharge;
                try {
                    totalShipCharge = Double.parseDouble(totalShipChargeStr);
                } catch (NumberFormatException e) {
                    continue; // Пропускаем некорректные значения
                }

                results.putIfAbsent(salesChannel, new double[3]);

                if (totalShipCharge > 0) {
                    results.get(salesChannel)[0] += totalShipCharge; // Сумма плюсов
                } else {
                    results.get(salesChannel)[1] += totalShipCharge; // Сумма минусов
                }
                results.get(salesChannel)[2] += totalShipCharge; // Общая сумма
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
