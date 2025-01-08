package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.ReportSettingsFactory;
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
                "01.07.2024 00:00:00",
                "31.12.2024 23:59:59",
                "dd.MM.yyyy HH:mm:ss");

        try {
            // Чтение данных из Excel-файла
            List<Map<String, String>> data = ExcelReader.readExcel(parameters.getFilePath(), 7);

            // Фильтрация по дате
            DateFilter dateFilter = new DateFilter("Datum/Uhrzeit", parameters.getStartDate(), parameters.getEndDate(), parameters.getDateFormat());
            List<Map<String, String>> filteredData = dateFilter.filter(data);

            // Обрабатываем и выводим отчеты
            processAndPrintReport(ReportSettingsFactory.createSellerShippingFeeReport(), filteredData);
            processAndPrintReport(ReportSettingsFactory.createAmazonShippingFeeReport(), filteredData);

        } catch (IOException | ParseException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Обрабатывает и выводит данные отчета.
     *
     * @param report       Настройка отчета, включая название, фильтры и колонки для подсчёта.
     * @param filteredData Отфильтрованные данные, готовые для дальнейшей обработки.
     */
    private static void processAndPrintReport(ReportSetting report, List<Map<String, String>> filteredData) {
        // Применяем фильтры к данным на основе настроек отчета
        List<Map<String, String>> reportData = report.applyFilters(filteredData);

        // Подсчитываем итоговую сумму по указанным колонкам
        Double totalSum = report.calculateSums(reportData);

        // Выводим название отчета и итоговую сумму в консоль
        System.out.println(report.getName());
        System.out.println(totalSum);
    }
}
