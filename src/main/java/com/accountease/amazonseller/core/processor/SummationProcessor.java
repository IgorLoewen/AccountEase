package com.accountease.amazonseller.core.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummationProcessor {

    /**
     * Calculates the positive and negative sums for specified numeric columns.
     *
     * @param data          The list of rows (each row is a map of column names to values).
     * @param numericColumns List of column names to calculate sums for (varargs).
     * @return A map where each key is a column name, and each value is another map with positive and negative sums.
     */
    public Map<String, Map<String, Double>> calculateSums(List<Map<String, String>> data, String... numericColumns) {
        Map<String, Map<String, Double>> columnSums = new HashMap<>();

        // Initialize sums for each column
        for (String column : numericColumns) {
            Map<String, Double> sums = new HashMap<>();
            sums.put("positive", 0.0);
            sums.put("negative", 0.0);
            columnSums.put(column, sums);
        }

        // Process each row
        for (Map<String, String> row : data) {
            for (String column : numericColumns) {
                String valueStr = row.getOrDefault(column, "").replace(",", ".").trim();
                if (valueStr.isEmpty()) continue;

                try {
                    double value = Double.parseDouble(valueStr);
                    if (value > 0) {
                        columnSums.get(column).put("positive", columnSums.get(column).get("positive") + value);
                    } else if (value < 0) {
                        columnSums.get(column).put("negative", columnSums.get(column).get("negative") + value);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка преобразования значения \"" + valueStr + "\" в колонке \"" + column + "\".");
                }
            }
        }

        return columnSums;
    }
}
