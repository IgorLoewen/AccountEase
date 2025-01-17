package com.accountease.amazonseller.core.processor;

import java.util.List;
import java.util.Map;

public class SummationProcessor {

    /**
     * Class SummationProcessor
     *
     * This class is designed to calculate the total sum of numeric values from specified columns in a data table.
     *
     * Method calculateTotalSum:
     * 1. Accepts a list of data (each row is a Map of column names and values) and a list of columns to sum.
     * 2. Checks the data and column list for null or emptiness. If they are empty, returns 0.0.
     * 3. Sums the values of the specified columns, ignoring empty or invalid values.
     * 4. Logs an error message to the console when a value fails to parse as a number.
     *
     * Key Features:
     * - Handles empty data, empty column lists, or null inputs.
     * - Ignores invalid values (e.g., text instead of numbers).
     * - Supports numbers with commas and spaces (replaces commas with dots, trims spaces).
     *
     * Example:
     * Data: [{"columnA": "10", "columnB": "20.5"}, {"columnA": "invalid", "columnB": "15"}]
     * Columns: ["columnA", "columnB"]
     * Result: 10 + 20.5 + 15 = 45.5
     *
     * Significance:
     * This method is used for summing numeric data in analytical tasks and reporting.
     */

    public Double calculateTotalSum(List<Map<String, String>> data, List<String> numericColumns) {
        if (data == null || numericColumns == null || numericColumns.isEmpty()) {
            return 0.0;
        }

        double totalSum = 0.0;

        for (Map<String, String> row : data) {
            for (String column : numericColumns) {
                String valueStr = row.getOrDefault(column, "").replace(",", ".").trim();
                if (valueStr.isEmpty()) continue;

                try {
                    double value = Double.parseDouble(valueStr);
                    totalSum += value;
                } catch (NumberFormatException e) {
                    System.err.println("Error converting value \"" + valueStr + "\" in column \"" + column + "\".");
                }
            }
        }

        return totalSum;
    }


}
