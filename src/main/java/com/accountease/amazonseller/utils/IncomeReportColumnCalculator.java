package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.ReportSettingsFactory;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        try {
            // Устанавливаем глобальные параметры
            ReportSetting.setGlobalParameters(
                    "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx",
                    "01.07.2024 00:00:00",
                    "31.12.2024 23:59:59",
                    "dd.MM.yyyy HH:mm:ss"
            );

            // Чтение данных из Excel-файла
            List<Map<String, String>> data = ExcelReader.readExcel(ReportSetting.getFilePath(), 7);

            // Обрабатываем и выводим отчеты
            processAndPrintReport(ReportSettingsFactory.createSellerShippingFeeReport(), data);
            processAndPrintReport(ReportSettingsFactory.createAmazonShippingFeeReport(), data);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Обрабатывает и выводит данные отчета.
     *
     * @param report Настройка отчета, включая название и фильтры.
     * @param data   Исходные данные.
     */
    private static void processAndPrintReport(ReportSetting report, List<Map<String, String>> data) {
        // Применяем фильтры к данным
        List<Map<String, String>> reportData = report.applyFilters(data);

        // Подсчитываем итоговую сумму по указанным колонкам
        Double totalSum = report.calculateSums(reportData);

        // Выводим название отчета и итоговую сумму в консоль
        System.out.println(report.getName());
        System.out.println(totalSum);
    }
}
