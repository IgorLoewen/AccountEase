package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelNumericColumnSum {

    public static void main(String[] args) {
        // Путь к файлу Excel
        String filePath = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист

            // Заголовки находятся на 8-й строке (индекс 7)
            Row headerRow = sheet.getRow(7);
            if (headerRow == null) {
                System.err.println("Не найдена строка с заголовками на 8-м ряду.");
                return;
            }

            // Чтение заголовков
            LinkedHashMap<String, Integer> columnIndexMap = new LinkedHashMap<>();
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String columnName = headerRow.getCell(i).getStringCellValue().trim();
                columnIndexMap.put(columnName, i);
            }

            // Список колонок с денежными операциями (исключая Postleitzahl)
            List<String> numericColumns = Arrays.asList(
                    "Umsätze", "Produktumsatzsteuer", "Gutschrift für Versandkosten",
                    "Steuer auf Versandgutschrift", "Gutschrift für Geschenkverpackung",
                    "Steuer auf Geschenkverpackungsgutschriften", "Rabatte aus Werbeaktionen",
                    "Steuer auf Aktionsrabatte", "Einbehaltenе Steuer auf Marketplace",
                    "Verkaufsgebühren", "Gebühren zu Versand durch Amazon", "Andere Transaktionsgebühren",
                    "Andere", "Gesamt"
            );

            // Хранение сумм для каждой колонки
            Map<String, Double> positiveSums = new HashMap<>();
            Map<String, Double> negativeSums = new HashMap<>();

            for (String column : numericColumns) {
                positiveSums.put(column, 0.0);
                negativeSums.put(column, 0.0);
            }

            // Суммирование значений
            for (int rowNum = 8; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                for (String column : numericColumns) {
                    Integer columnIndex = columnIndexMap.get(column);
                    if (columnIndex == null) continue;

                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        try {
                            // Преобразование строки в число (замена запятой на точку)
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
                            System.err.println("Ошибка преобразования значения в колонке " + column + " в строке " + (rowNum + 1));
                        }
                    }
                }
            }

            // Вывод результатов
            System.out.println("Суммы по колонкам:");
            for (String column : numericColumns) {
                System.out.println("Колонка: " + column);
                System.out.println("  Сумма положительных значений: " + positiveSums.get(column));
                System.out.println("  Сумма отрицательных значений: " + negativeSums.get(column));
            }

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
