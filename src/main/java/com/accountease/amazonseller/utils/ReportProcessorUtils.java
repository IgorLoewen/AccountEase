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
public class ReportProcessorUtils {

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
     * Creates a new ReportSetting object by extracting unique values from the last numeric column
     * of a given report and applying them as a filter to another report.
     *
     * @param firstReport   The source report from which unique values are extracted.
     * @param templateReport The template report to which the unique values are applied.
     * @return A new ReportSetting object with the applied unique value filter.
     */
    public static ReportSetting createFilteredReportFromAnother(
            ReportSetting firstReport,
            ReportSetting templateReport) {
        // Extract unique values from the first report
        UniqueValuesProcessor processor = new UniqueValuesProcessor();
        List<String> uniqueValues = processor.extractUniqueValuesFromLastNumericColumn(
                ReportSetting.getData(),
                firstReport.getNumericColumns(),
                firstReport.getColumnFilters()
        );

        // Apply the unique values to the template report
        return new ReportSetting(
                templateReport.getName(),
                Map.of(
                        templateReport.getColumnFilters().keySet().iterator().next(), uniqueValues
                ),
                templateReport.getNumericColumns()
        );
    }
}
