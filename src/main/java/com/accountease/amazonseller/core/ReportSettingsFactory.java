package com.accountease.amazonseller.core;

import java.util.List;
import java.util.Map;

public class ReportSettingsFactory {

    // Названия колонок
    private static final String COLUMN_TYP = "Typ";
    private static final String COLUMN_VERSAND = "Versand";
    private static final String COLUMN_BESCHREIBUNG = "Beschreibung";

    // Уникальные значения колонок
    private static final List<String> TYP_BESTELLUNG = List.of("Bestellung");

    private static final List<String> VERSAND_VERKAEUFER = List.of("Verkäufer");


    // Названия cуммарных итогов для вывода
    private static final List<String> NUMERIC_VERKAUFSGEBUEHREN = List.of("Verkaufsgebühren");


    public static ReportSetting getTotalSellerShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Verkäufer",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung"),
                        COLUMN_VERSAND, List.of("Verkäufer")
                ),
                List.of("Verkaufsgebühren")
        );
    }

    public static ReportSetting getTotalAmazonShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung"),
                        COLUMN_VERSAND, List.of("Amazon")
                ),
                List.of("Verkaufsgebühren")
        );
    }

    public static ReportSetting getTotalSalesSumSeller() {
        return new ReportSetting(
                "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung"),
                        COLUMN_VERSAND, List.of("Verkäufer")
                ),
                List.of("Umsätze")
        );
    }

    public static ReportSetting getTotalSalesSumAmazon() {
        return new ReportSetting(
                "Verkäufe mit Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung"),
                        COLUMN_VERSAND, List.of("Amazon")
                ),
                List.of("Umsätze")
        );
    }

    public static ReportSetting getTotalAdvertisingCosts() {
        return new ReportSetting(
                "Werbekosten",
                Map.of(
                        COLUMN_TYP, List.of("Servicegebühr"),
                        "Beschreibung", List.of("Werbekosten")
                ),
                List.of("Gesamt")
        );
    }

    public static ReportSetting getTotalAmazonFulfillmentFees() {
        return new ReportSetting(
                "Transaktionsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung")
                ),
                List.of("Gebühren zu Versand durch Amazon")
        );
    }

    public static ReportSetting getTotalPromotionalDiscountsFees() {
        return new ReportSetting(
                "Werbeaktionsrabatte",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung")
                ),
                List.of("Rabatte aus Werbeaktionen")
        );
    }

    public static ReportSetting getTotalAdjustmentsFees() {
        return new ReportSetting(
                "Anpassungen",
                Map.of(
                        COLUMN_TYP, List.of("Anpassung"),
                        "Beschreibung", List.of("Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung")
                ),
                List.of("Gesamt")
        );
    }

       // Описан случай, где выбор наоборот исключает колонки с выбора. Другие все выбираются!
    public static ReportSetting getTotalServiceFees() {
        return new ReportSetting(
                "Servicegebühren",
                Map.of(
                        COLUMN_TYP, List.of("Servicegebühr"),
                        "Beschreibung", List.of("!exclude", "Werbekosten")  // Исключающий фильтр
                ),
                List.of("Gesamt")
        );
    }

    public static ReportSetting getTotalStorageAndServiceFeesForAmazonFulfillment() {
        return new ReportSetting(
                "Lagerbestands- und Service-Gebühren für Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, List.of("Versand durch Amazon Lagergebühr")
                ),
                List.of("Gesamt")
        );
    }

    public static ReportSetting getTotalRefundsForShippingCredits() {
        return new ReportSetting(
                "Erstattungen für Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, List.of("Erstattung")
                ),
                List.of("Gutschrift für Versandkosten")
        );
    }

    public static ReportSetting getTotalRefundsForPromotionalDiscounts() {
        return new ReportSetting(
                "Erstattungen zur Werbeaktionsrabatt",
                Map.of(
                        COLUMN_TYP, List.of("Erstattung")
                ),
                List.of("Rabatte aus Werbeaktionen")
        );
    }

    public static ReportSetting getTotalShippingCreditNotes() {
        return new ReportSetting(
                "Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, List.of("Bestellung")
                ),
                List.of("Gutschrift für Versandkosten")
        );
    }

    // Описан случай, где выбор наоборот исключает колонки с выбора. Другие все выбираются!
    public static ReportSetting getTotalFBALogisticsInventoryCredits() {
        return new ReportSetting(
                "FBA Lagerbestandsguthaben",
                Map.of(
                        COLUMN_TYP, List.of("Anpassung"),
                        "Beschreibung", List.of("!exclude", "Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung","Versand durch Amazon Erstattung für Lagerbestand - Kundenrücksendung")  // Исключающий фильтр
                ),
                List.of("Gesamt")
        );
    }

    public static ReportSetting getTotalRefundsForAmazonShippedItems() {
        return new ReportSetting(
                "Erstattungen für durch Amazon versandte Artikel",
                Map.of(
                        COLUMN_TYP, List.of("Erstattung")
                ),
                List.of("Umsätze")
        );
    }

    public static ReportSetting getTotalRefundsForAmazonTransactionFees() {
        return new ReportSetting(
                "Ersattungen zur Transaktionsgebühr - Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, List.of("Erstattung")
                ),
                List.of("Gebühren zu Versand durch Amazon")
        );
    }

    public static ReportSetting getTotalRefundAmountForReturnedShipments() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        COLUMN_TYP, List.of("Erstattung")

                ),
                List.of("Verkaufsgebühren")
        );
    }


}
