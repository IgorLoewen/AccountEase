package com.accountease.amazonseller.core.processor;

import java.util.*;

/**
 * This class processes a list of rows to extract unique values from a specified column.
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
}

/// пример вызова уникальных значений для значения в колонке 'Typ' - вместо Typ можно другие названия подставлять
//
//            // Уникальные значения для колонки "Type"
//            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
//            List<String> uniqueValues = uniqueValuesProcessor.getUniqueValues(filteredData, "Typ");
//
//            // Выводим уникальные значения
//            System.out.println("Уникальные значения в колонке 'Typ':");
//            uniqueValues.forEach(System.out::println);