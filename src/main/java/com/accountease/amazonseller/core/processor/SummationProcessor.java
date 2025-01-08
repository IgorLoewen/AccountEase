package com.accountease.amazonseller.core.processor;

import java.util.List;
import java.util.Map;

public class SummationProcessor {

    /**
     * Calculates the total sum for specified numeric columns.
     *
     * @param data           The list of rows (each row is a map of column names to values).
     * @param numericColumns List of column names to calculate sums for.
     * @return The total sum as a Double.
     */
    public Double calculateTotalSum(List<Map<String, String>> data, List<String> numericColumns) {
        double totalSum = 0.0;

        for (Map<String, String> row : data) {
            for (String column : numericColumns) {
                String valueStr = row.getOrDefault(column, "").replace(",", ".").trim();
                if (valueStr.isEmpty()) continue;

                try {
                    double value = Double.parseDouble(valueStr);
                    totalSum += value;
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка преобразования значения \"" + valueStr + "\" в колонке \"" + column + "\".");
                }
            }
        }

        return totalSum;
    }

    /**
     * Overloaded method to calculate the total sum for a single column.
     *
     * @param data         The list of rows (each row is a map of column names to values).
     * @param singleColumn The column name to calculate the total sum for.
     * @return The total sum as a Double.
     */
    public Double calculateTotalSum(List<Map<String, String>> data, String singleColumn) {
        return calculateTotalSum(data, List.of(singleColumn));
    }
}
