package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.constants.FilterConstants;
import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.util.*;
import java.io.IOException;

public class ReportSetting {
    private final String name; // Название отчёта
    private final Map<String, List<String>> columnFilters; // Фильтры по колонкам
    private final List<String> numericColumns; // Колонки для подсчёта

    // Считываем данные из Excel (статическое поле)
    private static final List<Map<String, String>> data = initData();

    public ReportSetting(String name, Map<String,List<String>> columnFilters, List<String> numericColumns) {
        this.name = name;
        this.columnFilters = columnFilters;
        this.numericColumns = numericColumns;
    }

    // Метод инициализации data
    private static List<Map<String, String>> initData() {
        try {
            return ExcelReader.readExcel(FilterConstants.FILE_PATH, FilterConstants.HEADER_ROW_INDEX);
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
            DateFilter dateFilter = new DateFilter(
                    FilterConstants.DATE_COLUMN_EXCEL,
                    FilterConstants.START_DATE,
                    FilterConstants.END_DATE,
                    FilterConstants.DATE_FORMAT
            );
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


    @Override
    public String toString() {
        return "ReportSetting{name='" + name + '\'' +
                ", columnFilters=" + columnFilters +
                ", numericColumns=" + numericColumns +
                '}';
    }



}
