package com.accountease.amazonseller.core.processor;

import java.util.*;

/**
 * The UniqueValuesProcessor class provides utility methods to work with tabular data,
 * focusing on extracting unique values from columns and determining the last numeric column.
 *
 * It includes:
 * - Extraction of unique values from a specified column.
 * - Identification of the last numeric column in a given list.
 * - Extraction of unique values from the last numeric column after applying column filters.
 *
 * This class is designed to handle common scenarios in data processing, including
 * handling of null values and ensuring stability during operations.
 */

public class UniqueValuesProcessor {

    /**
     * Extracts unique values from the specified column in the provided data.
     *
     * @param data   The list of rows where each row is represented as a map of column names to values.
     * @param column The name of the column to extract unique values from.
     * @return A list of unique values extracted from the specified column.
     * @throws IllegalArgumentException if the data or column is null.
     *
     * This method ensures that:
     * - Only non-empty, trimmed values are considered.
     * - Duplicate values are removed.
     *
     * Example usage:
     * <pre>
     * List<Map<String, String>> data = List.of(
     *     Map.of("ColumnA", "value1", "ColumnB", "value2"),
     *     Map.of("ColumnA", "value1", "ColumnB", "value3")
     * );
     * List<String> uniqueValues = processor.getUniqueValues(data, "ColumnA");
     * // uniqueValues contains: ["value1"]
     * </pre>
     */

    public List<String> getUniqueValues(List<Map<String, String>> data, String column) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        if (column == null) {
            throw new IllegalArgumentException("Column name cannot be null.");
        }

        Set<String> uniqueValues = new LinkedHashSet<>();
        for (Map<String, String> row : data) {
            String value = row.getOrDefault(column, "").trim();
            if (!value.isEmpty()) {
                uniqueValues.add(value);
            }
        }

        return new ArrayList<>(uniqueValues);
    }

    /**
     * Determines the last numeric column from the provided list of numeric columns.
     *
     * @param numericColumns The list of numeric column names.
     * @return The name of the last numeric column in the list.
     * @throws IllegalStateException if the numericColumns list is null or empty.
     *
     * This method ensures:
     * - Stability by validating the input list.
     * - Accurate retrieval of the last element in the list.
     *
     * Example usage:
     * <pre>
     * List<String> numericColumns = List.of("Column1", "Column2", "Column3");
     * String lastColumn = processor.getLastNumericColumnName(numericColumns);
     * // lastColumn contains: "Column3"
     * </pre>
     */

    public String getLastNumericColumnName(List<String> numericColumns) {
        if (numericColumns == null || numericColumns.isEmpty()) {
            throw new IllegalStateException("The list of numeric columns is empty or null.");
        }
        return numericColumns.get(numericColumns.size() - 1);
    }

    /**
     * Extracts unique values from the last numeric column in the provided data after applying column filters.
     *
     * @param data          The list of rows where each row is represented as a map of column names to values.
     * @param numericColumns The list of numeric column names.
     * @param columnFilters  A map of column filters to apply to the data.
     * @return A list of unique values from the last numeric column after filtering.
     * @throws IllegalArgumentException if data, numericColumns, or columnFilters is null.
     * @throws RuntimeException if an error occurs during filtering or unique value extraction.
     *
     * This method performs the following steps:
     * 1. Identifies the last numeric column using {@link #getLastNumericColumnName(List)}.
     * 2. Filters the data based on the provided column filters using a {@code MultiColumnFilter}.
     * 3. Extracts unique values from the filtered data in the last numeric column using {@link #getUniqueValues(List, String)}.
     *
     * Example usage:
     * <pre>
     * List<Map<String, String>> data = List.of(
     *     Map.of("Column1", "10", "Column2", "A"),
     *     Map.of("Column1", "20", "Column2", "B"),
     *     Map.of("Column1", "10", "Column2", "C")
     * );
     * List<String> numericColumns = List.of("Column1");
     * Map<String, List<String>> columnFilters = Map.of("Column2", List.of("A", "B"));
     *
     * List<String> uniqueValues = processor.extractUniqueValuesFromLastNumericColumn(data, numericColumns, columnFilters);
     * // uniqueValues contains: ["10", "20"]
     * </pre>
     */

    public List<String> extractUniqueValuesFromLastNumericColumn(
            List<Map<String, String>> data,
            List<String> numericColumns,
            Map<String, List<String>> columnFilters) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        if (numericColumns == null || numericColumns.isEmpty()) {
            throw new IllegalArgumentException("Numeric columns cannot be null or empty.");
        }
        if (columnFilters == null) {
            throw new IllegalArgumentException("Column filters cannot be null.");
        }

        try {
            // Determine the last numeric column name
            String lastNumericColumnName = getLastNumericColumnName(numericColumns);

            // Apply filters to the data
            MultiColumnFilter filter = new MultiColumnFilter();
            List<Map<String, String>> filteredData = filter.filterByColumns(columnFilters, data);

            // Extract unique values from the filtered data
            return getUniqueValues(filteredData, lastNumericColumnName);
        } catch (Exception e) {
            throw new RuntimeException("Error extracting unique values from the last numeric column: " + e.getMessage(), e);
        }
    }
}
