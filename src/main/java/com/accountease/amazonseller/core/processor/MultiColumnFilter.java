package com.accountease.amazonseller.core.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiColumnFilter {

    /**
     * Filters the data based on multiple columns, each with its own filter values.
     *
     * @param data          The list of rows (each row is a map of column names to values).
     * @param columnFilters A map where the key is the column name, and the value is a list of filter values for that column.
     * @return A filtered list of rows where the row matches all specified column filters.
     */
    public List<Map<String, String>> filterByColumns(Map<String, List<String>> columnFilters, List<Map<String, String>> data) {
        List<Map<String, String>> filteredData = new ArrayList<>(data);

        for (Map.Entry<String, List<String>> entry : columnFilters.entrySet()) {
            String columnName = entry.getKey();
            List<String> filterValues = entry.getValue();

            filteredData = filterBySingleColumn(filteredData, columnName, filterValues);
        }

        return filteredData;
    }

    /**
     * Filters the data based on a single column and its filter values.
     *
     * @param data        The list of rows (each row is a map of column names to values).
     * @param columnName  The column to filter by.
     * @param filterValues The filter values for the column.
     * @return A filtered list of rows.
     */
    private List<Map<String, String>> filterBySingleColumn(List<Map<String, String>> data, String columnName, List<String> filterValues) {
        List<Map<String, String>> filteredData = new ArrayList<>();

        for (Map<String, String> row : data) {
            String cellValue = row.getOrDefault(columnName, "").trim();
            if (filterValues.isEmpty() || filterValues.contains(cellValue)) {
                filteredData.add(row);
            }
        }

        return filteredData;
    }
}
