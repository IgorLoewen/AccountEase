package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportSettingsFactory;
import com.accountease.amazonseller.core.ReportSetting;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {
        try {
            // Вызываем обработку отчётов напрямую
            processAndPrintReport(ReportSettingsFactory.getTotalSellerShippingFee());
            processAndPrintReport(ReportSettingsFactory.getTotalAmazonShippingFee());
            processAndPrintReport(ReportSettingsFactory.getTotalSalesSumSeller());
            processAndPrintReport(ReportSettingsFactory.getTotalSalesSumAmazon());
            processAndPrintReport(ReportSettingsFactory.getTotalAdvertisingCosts());
            processAndPrintReport(ReportSettingsFactory.getTotalAmazonFulfillmentFees());
            processAndPrintReport(ReportSettingsFactory.getTotalPromotionalDiscountsFees());
            processAndPrintReport(ReportSettingsFactory.getTotalAdjustmentsFees());
            processAndPrintReport(ReportSettingsFactory.getTotalServiceFees());
            processAndPrintReport(ReportSettingsFactory.getTotalStorageAndServiceFeesForAmazonFulfillment());
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForShippingCredits());
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForPromotionalDiscounts());
            processAndPrintReport(ReportSettingsFactory.getTotalShippingCreditNotes());
            processAndPrintReport(ReportSettingsFactory.getTotalFBALogisticsInventoryCredits());
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForAmazonShippedItems());
            processAndPrintReport(ReportSettingsFactory.getTotalRefundsForAmazonTransactionFees());
            processAndPrintReport(ReportSettingsFactory.getTotalRefundAmountForReturnedShipments());
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
