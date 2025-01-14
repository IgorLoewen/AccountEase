package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;

import java.util.List;
import java.util.Map;

/**
 * Utility class for processing reports and applying filters.
 * This class provides methods to create new filtered ReportSetting objects
 * based on unique values extracted from other reports.
 */
public class ReportProcessingTools {

    /**
     * Creates a new ReportSetting object by applying a list of unique values to a specific column filter.
     *
     * @param uniqueValues The list of unique values to apply.
     * @param columnName   The name of the column to which the values should be applied.
     * @param reportName   The name of the resulting report.
     * @param numericColumns The list of numeric columns for aggregation.
     * @return A new ReportSetting object with the applied filter.
     */
    public static ReportSetting buildFilterFromUniqueColumnValues(
            List<String> uniqueValues,
            String columnName,
            String reportName,
            List<String> numericColumns) {
        return new ReportSetting(
                reportName,
                Map.of(columnName, uniqueValues),
                numericColumns
        );
    }

    /**
     * Обрабатывает два отчёта, извлекает уникальные значения из первого и применяет их к шаблонному отчёту.
     *
     * @param firstReport    Исходный отчёт, из которого извлекаются уникальные значения.
     * @param templateReport Шаблонный отчёт, к которому применяются уникальные значения.
     * @return Новый ReportSetting с применённой фильтрацией.
     */
    public static ReportSetting processAndFilterWithUniqueValues(
            ReportSetting firstReport,
            ReportSetting templateReport) {
        // Извлекаем уникальные значения из первого отчёта
        UniqueValuesProcessor processor = new UniqueValuesProcessor();
        List<String> uniqueValues = processor.extractUniqueValuesFromLastNumericColumn(
                ReportSetting.getData(),
                firstReport.getNumericColumns(),
                firstReport.getColumnFilters()
        );

        // Создаём новый отчёт с применённой фильтрацией
        return new ReportSetting(
                templateReport.getName(),
                Map.of(
                        templateReport.getColumnFilters().keySet().iterator().next(), uniqueValues
                ),
                templateReport.getNumericColumns()
        );
    }

}
