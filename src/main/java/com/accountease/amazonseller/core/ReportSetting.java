package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ReportSetting {
    private final String name; // Название отчёта
    private final Map<String, List<String>> columnFilters; // Фильтры по колонкам
    private final List<String> numericColumns; // Колонки для подсчёта

    // Глобальные параметры для обработки данных
    private static final String filePath = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx"; // Путь к файлу
    private static final String startDate = "01.07.2024 00:00:00"; // Начальная дата
    private static final String endDate = "31.12.2024 23:59:59"; // Конечная дата
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); // Формат даты

    public ReportSetting(String name, Map<String, List<String>> columnFilters, List<String> numericColumns) {
        this.name = name;
        this.columnFilters = columnFilters;
        this.numericColumns = numericColumns;
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

    public static String getFilePath() {
        return filePath;
    }

    public static String getStartDate() {
        return startDate;
    }

    public static String getEndDate() {
        return endDate;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Выполняет фильтрацию данных (по дате и колонкам).
     *
     * @param data Исходные данные.
     * @return Отфильтрованные данные.
     */
    public List<Map<String, String>> applyFilters(List<Map<String, String>> data) {
        try {
            // Фильтрация по дате
            DateFilter dateFilter = new DateFilter("Datum/Uhrzeit", startDate, endDate, dateFormat);
            List<Map<String, String>> dateFilteredData = dateFilter.filter(data);

            // Фильтрация по колонкам
            MultiColumnFilter filter = new MultiColumnFilter();
            return filter.filterByColumns(columnFilters, dateFilteredData);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка фильтрации данных: " + e.getMessage(), e);
        }
    }

    /**
     * Подсчитывает сумму для указанных колонок.
     *
     * @param filteredData Отфильтрованные данные.
     * @return Общая сумма для всех колонок.
     */
    public Double calculateSums(List<Map<String, String>> filteredData) {
        SummationProcessor processor = new SummationProcessor();
        return processor.calculateTotalSum(filteredData, numericColumns);
    }
}
