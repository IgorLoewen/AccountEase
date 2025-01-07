package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.ReportSettingsFactory;
import com.accountease.amazonseller.core.processor.*;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        // Создаём параметры обработки
        ProcessingParameters parameters = new ProcessingParameters(
                "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx",
                "01.07.2024 00:00:00",
                "31.12.2024 23:59:59",
                "dd.MM.yyyy HH:mm:ss");

        try {
            // Чтение данных из Excel-файла
            List<Map<String, String>> data = ExcelReader.readExcel(parameters.getFilePath(), 7);

            // Фильтрация по дате
            DateFilter dateFilter = new DateFilter("Datum/Uhrzeit", parameters.getStartDate(), parameters.getEndDate(), parameters.getDateFormat());
            List<Map<String, String>> filteredData = dateFilter.filter(data);

            // Выбираем отчёт (например, продажи)
            ReportSetting report = ReportSettingsFactory.createSalesReport();

            // Применяем фильтры
            List<Map<String, String>> reportData = report.applyFilters(filteredData);

            // Подсчитываем суммы
            Map<String, Map<String, Double>> sums = report.calculateSums(reportData);
            Map<String, Double> totalSums = report.getSummedValues(reportData);

            // Выводим результаты
            System.out.println("Результаты для отчёта: " + report.getName());
            sums.forEach((column, sumMap) -> {
                System.out.println("Колонка: " + column);
                System.out.println("  Положительная сумма: " + sumMap.get("positive"));
                System.out.println("  Отрицательная сумма: " + sumMap.get("negative"));
            });

            System.out.println("Итоговые суммы:");
            System.out.println("  Общая положительная сумма: " + totalSums.get("totalPositive"));
            System.out.println("  Общая отрицательная сумма: " + totalSums.get("totalNegative"));

        } catch (IOException | ParseException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}