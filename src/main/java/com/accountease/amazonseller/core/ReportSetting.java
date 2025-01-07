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
     * Подсчитывает суммы для указанных колонок.
     *
     * @param filteredData Отфильтрованные данные.
     * @return Суммы для колонок.
     */
    public Map<String, Map<String, Double>> calculateSums(List<Map<String, String>> filteredData) {
        SummationProcessor processor = new SummationProcessor();
        return processor.calculateSums(filteredData, numericColumns);
    }

    /**
     * Возвращает отдельно суммы плюсов и минусов по всем указанным колонкам.
     *
     * @param filteredData Отфильтрованные данные.
     * @return Map с итоговыми плюсовыми и минусовыми суммами.
     */
    public Map<String, Double> getSummedValues(List<Map<String, String>> filteredData) {
        SummationProcessor processor = new SummationProcessor();
        Map<String, Map<String, Double>> sums = processor.calculateSums(filteredData, numericColumns);

        double totalPositive = sums.values().stream()
                .mapToDouble(sumMap -> sumMap.get("positive"))
                .sum();

        double totalNegative = sums.values().stream()
                .mapToDouble(sumMap -> sumMap.get("negative"))
                .sum();

        return Map.of("totalPositive", totalPositive, "totalNegative", totalNegative);
    }
}
