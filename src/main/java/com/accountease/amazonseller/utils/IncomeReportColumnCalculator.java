package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.ReportFilterSettings;
import com.accountease.amazonseller.core.ReportSetting;


import java.util.Collections;


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






            // Делаем исходный репорт с начальной фильтрацией
            ReportSetting firstReport = ReportFilterSettings.getUniqueValuesFromFilteredColumn();
            // Потом делаем фильтр куда будет передаваться первая фильтрация
            ReportSetting templateReport = ReportFilterSettings.buildFilterFromUniqueColumnValues(Collections.emptyList());
            // фильтруем уже по уникальным значениям по двум объектам выше
            ReportSetting finalReport = ReportFilterSettings.createFilteredReportFromAnother(firstReport, templateReport);

            // Обрабатываем отчёт
            processAndPrintReport(finalReport);

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
