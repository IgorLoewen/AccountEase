package com.accountease.amazonseller.core.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiColumnFilter {



    /**
     * Method filterByColumns.
     *
     * This method filters data based on multiple columns using the provided filters.
     * The logic of the method is built around sequential processing of columns and their filter values.
     *
     * Logic:
     * 1. The method takes a list of data (`data`), where each row is represented as a map of column names and their values,
     *    and a map of filters (`columnFilters`), where the key is the column name and the value is a list of filter values.
     * 2. If the data (`data`) is `null` or empty, the method returns an empty list.
     * 3. If the filters (`columnFilters`) are `null` or empty, the method returns a copy of all provided data.
     * 4. For each column in `columnFilters`, the method calls the helper method `filterBySingleColumn`,
     *    which performs filtering for the specified column.
     * 5. The result of the method is a list of rows that satisfy all the specified filtering conditions.
     *
     * Importance:
     * - This method implements the core business logic for data filtering.
     * - Its functionality is critical for the correct processing of tables and applying complex filtering conditions.
     * - The method is resilient to edge cases, such as empty or `null` input data and filters.
     *
     * Parameters:
     * @param data          A list of rows (each row is a map of column names and their values).
     * @param columnFilters A map of filters where the key is the column name and the value is a list of filter values.
     *
     * @return A filtered list of rows that match the specified filters. If the data or filters are empty,
     *         an empty list or a copy of the provided data is returned.
     */

    public List<Map<String, String>> filterByColumns(Map<String, List<String>> columnFilters, List<Map<String, String>> data) {
        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }

        if (columnFilters == null || columnFilters.isEmpty()) {
            return new ArrayList<>(data);
        }

        List<Map<String, String>> filteredData = new ArrayList<>(data);

        for (Map.Entry<String, List<String>> entry : columnFilters.entrySet()) {
            String columnName = entry.getKey();
            List<String> filterValues = entry.getValue();

            filteredData = filterBySingleColumn(filteredData, columnName, filterValues);
        }

        return filteredData;
    }

    /**
     * Method filterBySingleColumn.
     *
     * This method filters data based on a single column using the provided filter values.
     *
     * Logic:
     * 1. The method checks if the filter is an exclude filter (`!exclude`).
     * 2. If the filter is an exclude filter, rows with values matching the filter (except `!exclude`) are excluded from the result.
     * 3. If the filter is an include filter, rows with values present in the filter are added to the result.
     * 4. If the filter list is empty or `null`, no filtering is applied.
     *
     * Importance:
     * - This method implements the core filtering logic for a single column.
     * - It is a helper method and is called for each column individually during the filtering process.
     *
     * Parameters:
     * @param data         List of rows (each row is a map of column names and their values).
     * @param columnName   Name of the column to filter by.
     * @param filterValues List of filter values for the specified column.
     *
     * @return A filtered list of rows that meet the filtering criteria.
     */

    private List<Map<String, String>> filterBySingleColumn(List<Map<String, String>> data, String columnName, List<String> filterValues) {
        List<Map<String, String>> filteredData = new ArrayList<>();

        // Check if it's an "exclude filter" (contains "!exclude")
        boolean isExcludeFilter = filterValues != null && !filterValues.isEmpty() && filterValues.contains("!exclude");

        for (Map<String, String> row : data) {
            String cellValue = row.getOrDefault(columnName, "").trim();

            if (isExcludeFilter) {
                // Exclude rows where the value matches any filter value (except "!exclude")
                boolean matchesExclude = filterValues.stream()
                        .filter(value -> !value.equals("!exclude"))
                        .anyMatch(value -> value.equals(cellValue));
                if (!matchesExclude) {
                    filteredData.add(row);
                }
            } else {
                // Include rows where the value matches any filter value
                if (filterValues == null || filterValues.isEmpty() || filterValues.contains(cellValue)) {
                    filteredData.add(row);
                }
            }
        }

        return filteredData;
    }

}
