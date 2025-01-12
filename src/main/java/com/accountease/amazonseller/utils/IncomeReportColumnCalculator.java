package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportFilterSettings;
import com.accountease.amazonseller.core.ReportSetting;

import java.util.List;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        try {
            // Вызываем обработку отчётов напрямую
            processAndPrintReport(ReportFilterSettings.getTotalSellerShippingFee());
            processAndPrintReport(ReportFilterSettings.getTotalAmazonShippingFee());
            processAndPrintReport(ReportFilterSettings.getTotalSalesSumSeller());
            processAndPrintReport(ReportFilterSettings.getTotalSalesSumAmazon());
            processAndPrintReport(ReportFilterSettings.getTotalAdvertisingCosts());
            processAndPrintReport(ReportFilterSettings.getTotalAmazonFulfillmentFees());
            processAndPrintReport(ReportFilterSettings.getTotalPromotionalDiscountsFees());
            processAndPrintReport(ReportFilterSettings.getTotalAdjustmentsFees());
            processAndPrintReport(ReportFilterSettings.getTotalServiceFees());
            processAndPrintReport(ReportFilterSettings.getTotalStorageAndServiceFeesForAmazonFulfillment());
            processAndPrintReport(ReportFilterSettings.getTotalRefundsForShippingCredits());
            processAndPrintReport(ReportFilterSettings.getTotalRefundsForPromotionalDiscounts());
            processAndPrintReport(ReportFilterSettings.getTotalShippingCreditNotes());
            processAndPrintReport(ReportFilterSettings.getTotalFBALogisticsInventoryCredits());
            processAndPrintReport(ReportFilterSettings.getTotalRefundsForAmazonShippedItems());
            processAndPrintReport(ReportFilterSettings.getTotalRefundsForAmazonTransactionFees());
            processAndPrintReport(ReportFilterSettings.getTotalRefundAmountForReturnedShipments());


            // Тестируем уникальные значения для последней колонки NUMERIC_VERKAUFSGEBUEHREN
            getUniqueValuesFromReportFilterSettings(ReportFilterSettings.getUniqueValuesFromFilteredColumn());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void processAndPrintReport(ReportSetting report) {
        // Обработка отчёта
        Double totalSum = report.processReport();

        // Вывод результата
        System.out.println(report.getName());
        System.out.println(totalSum);
    }

    private static void getUniqueValuesFromReportFilterSettings(ReportSetting report) {
        try {
            // Извлекаем уникальные значения из последней колонки NUMERIC_VERKAUFSGEBUEHREN
            List<String> uniqueValues = report.extractUniqueValuesFromLastNumericColumn();

            // Тестовый вывод
            System.out.println(report.getName());
            System.out.println("Уникальные значения: " + String.join(", ", uniqueValues));
        } catch (Exception e) {
            System.err.println("Ошибка в testUniqueValues: " + e.getMessage());
        }
    }
}
