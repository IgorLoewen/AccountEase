package com.accountease.amazonseller.core.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ColumnFilter {

    /**
     * Filters the data based on a column and one or more filter values.
     *
     * @param data        The list of rows (each row is a map of column names to values).
     * @param columnName  The name of the column to filter by.
     * @param filterValues One or more values to filter the column by.
     * @return A filtered list of rows where the column value matches one of the filter values.
     */
    public List<Map<String, String>> filterByColumn(List<Map<String, String>> data, String columnName, String... filterValues) {
        List<Map<String, String>> filteredData = new ArrayList<>();

        List<String> filterList = Arrays.asList(filterValues);
        for (Map<String, String> row : data) {
            String cellValue = row.getOrDefault(columnName, "").trim();
            if (filterList.contains(cellValue)) {
                filteredData.add(row);
            }
        }

        return filteredData;
    }
}
