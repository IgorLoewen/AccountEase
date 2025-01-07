package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.processor.*;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        // Создаём параметры обработки
        ProcessingParameters parameters = new ProcessingParameters(
                "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx",
                "01.08.2024 00:00:00",
                "22.12.2024 23:59:59",
                "dd.MM.yyyy HH:mm:ss"
        );

        try {
            // Чтение данных из Excel-файла
            List<Map<String, String>> data = ExcelReader.readExcel(parameters.getFilePath(), 7);

            // Фильтрация по дате
            DateFilter dateFilter = new DateFilter(
                    "Datum/Uhrzeit",
                    parameters.getStartDate(),
                    parameters.getEndDate(),
                    parameters.getDateFormat()
            );
            List<Map<String, String>> filteredData = dateFilter.filter(data);

            // Уникальные значения для колонки "Type"
            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
            List<String> uniqueValues = uniqueValuesProcessor.getUniqueValues(filteredData, "Type");

            // Выводим уникальные значения
            System.out.println("Уникальные значения в колонке 'Type':");
            uniqueValues.forEach(System.out::println);

            // Настраиваем фильтр для колонки "Type"
            ColumnFilter columnFilter = new ColumnFilter();
            List<Map<String, String>> typeFilteredData = columnFilter.filterByColumn(
                    filteredData,
                    "Typ",
                    Arrays.asList("Übertrag", "Anpassung") // Значения для фильтрации
            );

            // Подсчёт сумм после фильтрации
            SummationProcessor summationProcessor = new SummationProcessor();
            List<String> numericColumns = Arrays.asList(
                    "Andere", "Gesamt"
            );
            Map<String, Map<String, Double>> sums = summationProcessor.calculateSums(typeFilteredData, numericColumns);

            System.out.println("Данные для подсчёта сумм:");
            typeFilteredData.forEach(System.out::println);


// Вывод результатов подсчёта
            System.out.println("Результаты подсчёта сумм после фильтрации:");
            sums.forEach((column, sumMap) -> {
                System.out.println("Колонка: " + column);
                System.out.println("  Положительная сумма: " + sumMap.get("positive"));
                System.out.println("  Отрицательная сумма: " + sumMap.get("negative"));
            });


        } catch (IOException | ParseException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
