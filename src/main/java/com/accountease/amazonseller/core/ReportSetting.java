package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;

import java.util.List;
import java.util.Map;

public class ReportSetting {
    private final String name; // Название отчёта
    private final Map<String, List<String>> columnFilters; // Фильтры по колонкам
    private final List<String> numericColumns; // Колонки для подсчёта

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

    /**
     * Выполняет фильтрацию данных.
     *
     * @param data Исходные данные.
     * @return Отфильтрованные данные.
     */
    public List<Map<String, String>> applyFilters(List<Map<String, String>> data) {
        MultiColumnFilter filter = new MultiColumnFilter();
        return filter.filterByColumns(columnFilters, data);
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
