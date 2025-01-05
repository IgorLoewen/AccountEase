package com.example.accountease.amazonseller.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AmazonExcelFilter {

    public static void main(String[] args) {
        // Путь к папке Desktop PC bis 2023
        String desktopPath = "/Users/GiorUg/Desktop/Desktop PC bis 2023";

        // Имя файла Excel
        String inputFileName = "7-12.24AmazonUmsaetze.xlsx";
        String inputFilePath = desktopPath + File.separator + inputFileName;

        // Номер заказа для фильтрации
        String transactionEventID = "028-6901747-5745101"; // Пример номера

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Предполагаем, что данные на первом листе
            List<String[]> filteredData = new ArrayList<>();

            // Чтение заголовков (первая строка)
            Row headerRow = sheet.getRow(0);
            String[] headers = new String[headerRow.getPhysicalNumberOfCells()];
            for (int i = 0; i < headers.length; i++) {
                headers[i] = headerRow.getCell(i).getStringCellValue().toUpperCase().replaceAll("\\s+", "");
            }

            // Поиск индекса колонки "TRANSACTION_EVENT_ID"
            int transactionEventIDIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals("TRANSACTION_EVENT_ID")) {
                    transactionEventIDIndex = i;
                    break;
                }
            }

            if (transactionEventIDIndex == -1) {
                System.err.println("Колонка 'TRANSACTION_EVENT_ID' не найдена.");
                return;
            }

            // Фильтрация данных
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) continue;

                Cell cell = row.getCell(transactionEventIDIndex);
                if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(transactionEventID)) {
                    String[] rowData = new String[headers.length];
                    for (int i = 0; i < headers.length; i++) {
                        Cell dataCell = row.getCell(i);
                        rowData[i] = (dataCell != null) ? dataCell.toString() : "";
                    }
                    filteredData.add(rowData);
                }
            }

            // Вывод результатов
            System.out.println("Данные для TRANSACTION_EVENT_ID: " + transactionEventID);
            for (String[] row : filteredData) {
                System.out.println(String.join(" | ", row));
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
