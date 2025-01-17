package com.accountease.amazonseller.core.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    /**
     * Class ExcelReader
     *
     * This class is responsible for reading data from an Excel file and converting it into a list of maps,
     * where each map represents a row, and the keys are column headers.
     *
     * Method readExcel:
     * 1. Reads the data from the first sheet of the Excel file specified by the file path.
     * 2. Extracts the column headers from the row specified by `headerRowIndex`.
     * 3. Processes all rows after the header row and converts them into a map of column headers to cell values.
     *
     * Key Features:
     * - Handles empty cells by storing them as empty strings in the resulting map.
     * - Skips rows that are entirely empty.
     * - Throws an `IllegalArgumentException` if the header row is missing or `headerRowIndex` is incorrect.
     * - Supports files in `.xlsx` format using Apache POI.
     *
     * Parameters:
     * @param filePath       The path to the Excel file.
     * @param headerRowIndex The index of the row containing column headers (0-based).
     *
     * Returns:
     * - A list of maps, where each map represents a row in the Excel file. Keys are column names, and values are cell data.
     *
     * Example:
     * For an Excel file with the following content:
     * ```
     * Name     Age    Country
     * John     25     USA
     * Jane     30     UK
     * ```
     * Calling `readExcel("example.xlsx", 0)` will return:
     * ```
     * [
     *   {"Name": "John", "Age": "25", "Country": "USA"},
     *   {"Name": "Jane", "Age": "30", "Country": "UK"}
     * ]
     * ```
     *
     * Significance:
     * - This method provides a reliable way to extract structured data from Excel files for further processing.
     * - It ensures flexibility in handling diverse file contents, including missing cells and empty rows.
     */

    public static List<Map<String, String>> readExcel(String filePath, int headerRowIndex) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

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
