package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.processor.*;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.io.IOException;
import java.text.ParseException;
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

            // Настраиваем фильтр для колонки "Typ"
            ColumnFilter columnFilter = new ColumnFilter();

//                        // Уникальные значения для колонки "Versand"
//            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
//            List<String> uniqueValues = uniqueValuesProcessor.getUniqueValues(filteredData, "Versand");
//
//            // Выводим уникальные значения
//            System.out.println("Уникальные значения в колонке 'Versand':");
//            uniqueValues.forEach(System.out::println);


            // Несколько фильтров. Можно одну колонку выбирать, а можно несколько
            List<Map<String, String>> typeFilteredData = columnFilter.filterByColumn(
                    filteredData,
                    "Typ",
                     "Servicegebühr", "Verbindlichkeit", "Versand durch Amazon Lagergebühr", "Bestellung", "Übertrag", "Anpassung"
            );


            // Подсчёт сумм после фильтрации. Можно одну колонку выбирать, а можно несколько
            SummationProcessor summationProcessor = new SummationProcessor();
            Map<String, Map<String, Double>> sums = summationProcessor.calculateSums(
                    typeFilteredData,
                    "Rabatte aus Werbeaktionen"
            );
//
//            System.out.println("Данные для подсчёта сумм:");
//            typeFilteredData.forEach(System.out::println);

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
