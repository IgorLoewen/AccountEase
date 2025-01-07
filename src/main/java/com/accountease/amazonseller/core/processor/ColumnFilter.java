package com.accountease.amazonseller.core.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColumnFilter {

    public List<Map<String, String>> filterByColumn(List<Map<String, String>> data, String columnName, List<String> filterValues) {
        List<Map<String, String>> filteredData = new ArrayList<>();

        for (Map<String, String> row : data) {
            String cellValue = row.getOrDefault(columnName, "").trim();
            if (filterValues.contains(cellValue)) {
                filteredData.add(row); // Добавляем строку, если значение в колонке совпадает с фильтром
            }
        }

        return filteredData;
    }
}
