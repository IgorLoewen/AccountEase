package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;
import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class ReportSetting {
    private final String name; // Название отчёта
    private final Map<String, List<String>> columnFilters; // Фильтры по колонкам
    private final List<String> numericColumns; // Колонки для подсчёта

    // Глобальные параметры
    private static final String filePath = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx";
    private static final int headerRowIndex = 7; // Строка заголовков
    private static final String startDate = "01.07.2024 00:00:00";
    private static final String endDate = "31.12.2024 23:59:59";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final String DateColumnExel = "Datum/Uhrzeit";

    // Считываем данные из Excel (статическое поле)
    private static final List<Map<String, String>> data = initData();

    public ReportSetting(String name, Map<String, List<String>> columnFilters, List<String> numericColumns) {
        this.name = name;
        this.columnFilters = columnFilters;
        this.numericColumns = numericColumns;
    }

    // Метод инициализации data
    private static List<Map<String, String>> initData() {
        try {
            return ExcelReader.readExcel(filePath, headerRowIndex);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении Excel-файла: " + e.getMessage(), e);
        }
    }

    public String getName() {
        return name;
    }
    public Map<String, List<String>> getColumnFilters() {
        return columnFilters;
    }
    public List<String> getNumericColumns() {
        return numericColumns;
    }

    public static List<Map<String, String>> getData() {
        return data;
    }



    public Double processReport() {
        try {
            // Фильтрация данных
            DateFilter dateFilter = new DateFilter(DateColumnExel, startDate, endDate, dateFormat);
            List<Map<String, String>> dateFilteredData = dateFilter.filter(data);

            MultiColumnFilter filter = new MultiColumnFilter();
            List<Map<String, String>> filteredData = filter.filterByColumns(columnFilters, dateFilteredData);

            // Подсчёт суммы
            SummationProcessor processor = new SummationProcessor();
            return processor.calculateTotalSum(filteredData, numericColumns);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка обработки отчёта: " + e.getMessage(), e);
        }
    }


}
