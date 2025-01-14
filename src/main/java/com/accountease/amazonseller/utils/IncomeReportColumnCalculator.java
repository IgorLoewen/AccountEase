package com.accountease.amazonseller.utils;

import com.accountease.amazonseller.core.filters.ExclusionFilters;
import com.accountease.amazonseller.core.filters.StandartFilters;
import com.accountease.amazonseller.core.filters.ValueListFilters;
import com.accountease.amazonseller.core.ReportSetting;

import java.util.Collections;

public class IncomeReportColumnCalculator {

    public static void main(String[] args) {

        try {
            // Вызываем обработку отчётов напрямую
            processAndPrintReport(StandartFilters.getTotalSellerShippingFee());
            processAndPrintReport(StandartFilters.getTotalAmazonShippingFee());
            processAndPrintReport(StandartFilters.getTotalSalesSumSeller());
            processAndPrintReport(StandartFilters.getTotalSalesSumAmazon());
            processAndPrintReport(StandartFilters.getTotalAdvertisingCosts());
            processAndPrintReport(StandartFilters.getTotalAmazonFulfillmentFees());
            processAndPrintReport(StandartFilters.getTotalPromotionalDiscountsFees());
            processAndPrintReport(StandartFilters.getTotalAdjustmentsFees());
            processAndPrintReport(ExclusionFilters.getTotalServiceFees());
            processAndPrintReport(StandartFilters.getTotalStorageAndServiceFeesForAmazonFulfillment());
            processAndPrintReport(StandartFilters.getTotalRefundsForShippingCredits());
            processAndPrintReport(StandartFilters.getTotalRefundsForPromotionalDiscounts());
            processAndPrintReport(StandartFilters.getTotalShippingCreditNotes());
            processAndPrintReport(ExclusionFilters.getTotalFBALogisticsInventoryCredits());
            processAndPrintReport(StandartFilters.getTotalRefundsForAmazonShippedItems());
            processAndPrintReport(StandartFilters.getTotalRefundsForAmazonTransactionFees());
            processAndPrintReport(StandartFilters.getTotalRefundAmountForReturnedShipments());

            // Делаем исходный репорт с начальной фильтрацией
            ReportSetting firstReport = ValueListFilters.getUniqueValuesFromFilteredColumn();
            // Потом делаем фильтр, куда будет передаваться первая фильтрация
            ReportSetting templateReport = ValueListFilters.buildFilterFromUniqueColumnValues(Collections.emptyList());
            // Фильтруем уже по уникальным значениям по двум объектам выше
            ReportSetting finalReport = ReportProcessorUtils.createFilteredReportFromAnother(firstReport, templateReport);

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
