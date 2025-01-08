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
            // Чтение данных из Excel-файла начиная со строки 7 с заголовками
            List<Map<String, String>> data = ExcelReader.readExcel(ReportSetting.getFilePath(), 7);

            // Обрабатываем и выводим отчеты
            processAndPrintReport(ReportSettingsFactory.createSellerShippingFeeReport(), data);
            processAndPrintReport(ReportSettingsFactory.createAmazonShippingFeeReport(), data);
            processAndPrintReport(ReportSettingsFactory.createSalesReportSeller(), data);
            processAndPrintReport(ReportSettingsFactory.createSalesReportAmazon(), data);
            processAndPrintReport(ReportSettingsFactory.createAdvertisingCostsReport(), data);
            processAndPrintReport(ReportSettingsFactory.createAmazonFulfillmentFees(), data);
            processAndPrintReport(ReportSettingsFactory.createPromotionalDiscountsFees(), data);
            processAndPrintReport(ReportSettingsFactory.createAdjustmentsFees(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalServiceFees(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalStorageAndServiceFeesForAmazonFulfillment(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForShippingCredits(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForPromotionalDiscounts(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalShippingCreditNotes(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalFBALogisticsInventoryCredits(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForAmazonShippedItems(), data);
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForAmazonTransactionFees(), data);

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
