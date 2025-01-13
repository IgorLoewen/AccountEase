package com.accountease.amazonseller.core.processor;

import java.util.*;

/**
 * This class processes a list of rows to extract unique values from a specified column.
 * Additionally, it provides methods to determine the last numeric column and
 * extract unique values from that column after applying filters.
 */
public class UniqueValuesProcessor {

    /**
     * Extracts unique values from the specified column in the provided data.
     *
     * @param data   The list of rows (each row is a map of column names to values).
     * @param column The name of the column to extract unique values from.
     * @return A list of unique values.
     */
    public List<String> getUniqueValues(List<Map<String, String>> data, String column) {
        Set<String> uniqueValues = new HashSet<>();

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
     * @return The name of the last numeric column.
     * @throws IllegalStateException If the list of numeric columns is null or empty.
     */
    public String getLastNumericColumnName(List<String> numericColumns) {
        if (numericColumns == null || numericColumns.isEmpty()) {
            throw new IllegalStateException("The list of numeric columns is empty or null.");
        }
        return numericColumns.get(numericColumns.size() - 1);
    }

    /**
     * Extracts unique values from the last numeric column in the data after applying column filters.
     *
     * @param data          The list of rows (each row is a map of column names to values).
     * @param numericColumns The list of numeric column names.
     * @param columnFilters  A map of column filters to apply to the data.
     * @return A list of unique values from the last numeric column.
     * @throws RuntimeException If an error occurs during filtering or value extraction.
     */
    public List<String> extractUniqueValuesFromLastNumericColumn(List<Map<String, String>> data, List<String> numericColumns, Map<String, List<String>> columnFilters) {
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
