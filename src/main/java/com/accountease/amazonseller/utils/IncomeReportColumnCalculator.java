package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.ProcessingParameters;
import com.accountease.amazonseller.core.processor.SummationProcessor;
import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;
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
                "31.12.2024 23:59:59",
                "dd.MM.yyyy HH:mm:ss"
        );

        try {
            // Чтение данных из файла
            List<Map<String, String>> data = ExcelReader.readExcel(parameters.getFilePath(), 7);

            // Фильтрация по дате
            DateFilter dateFilter = new DateFilter(
                    "Datum/Uhrzeit",
                    parameters.getStartDate(),
                    parameters.getEndDate(),
                    parameters.getDateFormat()
            );
            List<Map<String, String>> filteredData = dateFilter.filter(data);

            // Уникальные значения для колонки "Typ"
            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
            List<String> uniqueValues = uniqueValuesProcessor.getUniqueValues(filteredData, "Typ");
            // Вывод уникальных значений
            System.out.println("Уникальные значения в колонке 'Typ':");
            uniqueValues.forEach(System.out::println);

            // Подсчёт сумм
            SummationProcessor summationProcessor = new SummationProcessor();
            List<String> numericColumns = Arrays.asList(
                    "Umsätze", "Produktumsatzsteuer", "Gutschrift für Versandkosten",
                    "Steuer auf Versandgutschrift", "Gutschrift für Geschenkverpackung",
                    "Steuer auf Geschenkverpackungsgutschriften", "Rabatte aus Werbeaktionen",
                    "Steuer auf Aktionsrabatte", "Einbehaltenе Steuer auf Marketplace",
                    "Verkaufsgebühren", "Gebühren zu Versand durch Amazon", "Andere Transaktionsgebühren",
                    "Andere", "Gesamt"
            );
            Map<String, Map<String, Double>> sums = summationProcessor.calculateSums(filteredData, numericColumns);

            // Вывод результатов
            System.out.println("Результаты подсчёта сумм:");
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
