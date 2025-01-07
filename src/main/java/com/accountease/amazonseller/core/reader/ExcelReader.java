package com.accountease.amazonseller.core.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    /**
     * Читает данные из Excel-файла и возвращает их в виде списка карт.
     * Каждая карта представляет строку, где ключами являются названия колонок.
     *
     * @param filePath       Путь к Excel-файлу.
     * @param headerRowIndex Индекс строки, содержащей заголовки колонок.
     * @return Список строк, представленных в виде карт.
     * @throws IOException Если при чтении файла произошла ошибка.
     */
    public static List<Map<String, String>> readExcel(String filePath, int headerRowIndex) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Первый лист
            Row headerRow = sheet.getRow(headerRowIndex);

            if (headerRow == null) {
                throw new IllegalArgumentException("Строка с заголовками не найдена по индексу: " + headerRowIndex);
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
