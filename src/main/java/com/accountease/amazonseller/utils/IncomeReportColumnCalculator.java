package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportFilterSettings;
import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;

import java.util.List;
import java.util.Map;

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

            // Тестируем уникальные значения для последней колонки
            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
            ReportSetting report = ReportFilterSettings.getUniqueValuesFromFilteredColumn();

            // Извлекаем уникальные значения из последней числовой колонки
            List<String> uniqueValues = uniqueValuesProcessor.extractUniqueValuesFromLastNumericColumn(
                    ReportSetting.getData(),
                    report.getNumericColumns(),
                    report.getColumnFilters()
            );

            // Выводим уникальные значения
            System.out.println(report.getName());
            System.out.println("Уникальные значения: " + String.join(", ", uniqueValues));
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
}
