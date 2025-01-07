package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.processor.*;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        // Создаём параметры обработки
        ProcessingParameters parameters = new ProcessingParameters(
                "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx",
                "12.11.2024 00:00:00",
                "12.11.2024 23:59:59",
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

            // Настраиваем фильтры для нескольких колонок
            MultiColumnFilter multiColumnFilter = new MultiColumnFilter();
            Map<String, List<String>> columnFilters = new HashMap<>();
//            columnFilters.put("Typ", List.of("Servicegebühr", "Verbindlichkeit", "Erstattung")); // Фильтры для "Typ"
//            columnFilters.put("Versand", Arrays.asList("Amazon"));        // Фильтры для "Versand"
//            columnFilters.put("Beschreibung", Arrays.asList(null, null));

            columnFilters.put("Typ", List.of("Servicegebühr", "Verbindlichkeit", "Erstattung","Bestellung"));
            columnFilters.put("Versand", List.of("Amazon","Verkäufer"));
            columnFilters.put("Ort der Bestellung", List.of("Aachen"));

            // Фильтрация по колонкам
            List<Map<String, String>> multiFilteredData = multiColumnFilter.filterByColumns(columnFilters, filteredData);

            SummationProcessor summationProcessor = new SummationProcessor();
            Map<String, Map<String, Double>> sums = summationProcessor.calculateSums(
                    multiFilteredData,
                    List.of("Steuer auf Aktionsrabatte", "Verkaufsgebühren")
            );

// Вывод результатов
            System.out.println("Результаты подсчёта для нескольких колонок:");
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
