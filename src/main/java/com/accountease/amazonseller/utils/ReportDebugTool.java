package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.filters.ExclusionFilters;
import com.accountease.amazonseller.core.filters.StandardFilters;
import com.accountease.amazonseller.core.filters.ValueListFilters;
import com.accountease.amazonseller.core.ReportSetting;

import java.util.Collections;

public class ReportDebugTool {

    public static void main(String[] args) {

        try {
            // Вызываем обработку отчётов напрямую
            processAndPrintReport(StandardFilters.getTotalSellerShippingFee());
            processAndPrintReport(StandardFilters.getTotalAmazonShippingFee());
            processAndPrintReport(StandardFilters.getTotalSalesSumSeller());
            processAndPrintReport(StandardFilters.getTotalSalesSumAmazon());
            processAndPrintReport(StandardFilters.getTotalAdvertisingCosts());
            processAndPrintReport(StandardFilters.getTotalAmazonFulfillmentFees());
            processAndPrintReport(StandardFilters.getTotalPromotionalDiscountsFees());
            processAndPrintReport(StandardFilters.getTotalAdjustmentsFees());
            processAndPrintReport(ExclusionFilters.getTotalServiceFees());
            processAndPrintReport(StandardFilters.getTotalStorageAndServiceFeesForAmazonFulfillment());
            processAndPrintReport(StandardFilters.getTotalRefundsForShippingCredits());
            processAndPrintReport(StandardFilters.getTotalRefundsForPromotionalDiscounts());
            processAndPrintReport(StandardFilters.getTotalShippingCreditNotes());
            processAndPrintReport(ExclusionFilters.getTotalFBALogisticsInventoryCredits());
            processAndPrintReport(StandardFilters.getTotalRefundsForAmazonShippedItems());
            processAndPrintReport(StandardFilters.getTotalRefundsForAmazonTransactionFees());
            processAndPrintReport(StandardFilters.getTotalRefundAmountForReturnedShipments());

            // Делаем финальный отчёт через метод
            ReportSetting finalReport = ReportProcessingTools.processAndFilterWithUniqueValues(
                    ValueListFilters.getUniqueValuesFromFilteredColumn(),
                    ValueListFilters.buildFilterFromUniqueColumnValues(Collections.emptyList())
            );

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
