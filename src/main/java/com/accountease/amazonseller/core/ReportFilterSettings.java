package com.accountease.amazonseller.core;

import java.util.List;
import java.util.Map;

public class ReportFilterSettings {

    // Названия колонок
    private static final String COLUMN_TYP = "Typ";
    private static final String COLUMN_VERSAND = "Versand";
    private static final String COLUMN_BESCHREIBUNG = "Beschreibung";
    private static final String COLUMN_BESTELLUNG = "Bestellung";

    // Уникальные значения колонок
    private static final List<String> TYP_BESTELLUNG = List.of("Bestellung");
    private static final List<String> TYP_SERVICEGEBUEHR = List.of("Servicegebühr");
    private static final List<String> TYP_ANPASSUNG = List.of("Anpassung");
    private static final List<String> TYP_LAGERGEBUEHR = List.of("Versand durch Amazon Lagergebühr");
    private static final List<String> TYP_ERSTATTUNG = List.of("Erstattung");

    private static final List<String> VERSAND_VERKAEUFER = List.of("Verkäufer");
    private static final List<String> VERSAND_AMAZON = List.of("Amazon");

    private static final List<String> BESCHREIBUNG_WERBEKOSTEN = List.of("Werbekosten");
    private static final List<String> BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG = List.of("Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung");

    // ❗ Особые случаи: исключающие фильтры
    private static final List<String> EXCLUDE_WERBEKOSTEN = List.of("!exclude", "Werbekosten");
    private static final List<String> EXCLUDE_ALLGEMEINE_ANPASSUNG = List.of("!exclude", "Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung", "Versand durch Amazon Erstattung für Lagerbestand - Kundenrücksendung");

    // Названия cуммарных итогов для вывода
    private static final List<String> NUMERIC_VERKAUFSGEBUEHREN = List.of("Verkaufsgebühren");
    private static final List<String> NUMERIC_UMSAETZE = List.of("Umsätze");
    private static final List<String> NUMERIC_GESAMT = List.of("Gesamt");
    private static final List<String> NUMERIC_GEBUEHREN_VERSAND_AMAZON = List.of("Gebühren zu Versand durch Amazon");
    private static final List<String> NUMERIC_RABATTE_AUS_WERBEAKTIONEN = List.of("Rabatte aus Werbeaktionen");
    private static final List<String> NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN = List.of("Gutschrift für Versandkosten");

    private static final List<String> UNIQUE_LIST_BESTELLNUMMER = List.of("Bestellnummer");


    public static ReportSetting getTotalSellerShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Verkäufer",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_VERKAEUFER
                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalAmazonShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_AMAZON
                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalSalesSumSeller() {
        return new ReportSetting(
                "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_VERKAEUFER
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalSalesSumAmazon() {
        return new ReportSetting(
                "Verkäufe mit Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_AMAZON
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalAdvertisingCosts() {
        return new ReportSetting(
                "Werbekosten",
                Map.of(
                        COLUMN_TYP, TYP_SERVICEGEBUEHR,
                        COLUMN_BESCHREIBUNG, BESCHREIBUNG_WERBEKOSTEN
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalAmazonFulfillmentFees() {
        return new ReportSetting(
                "Transaktionsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalPromotionalDiscountsFees() {
        return new ReportSetting(
                "Werbeaktionsrabatte",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalAdjustmentsFees() {
        return new ReportSetting(
                "Anpassungen",
                Map.of(
                        COLUMN_TYP, TYP_ANPASSUNG,
                        COLUMN_BESCHREIBUNG, BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG
                ),
                NUMERIC_GESAMT
        );
    }

    // Описан случай, где выбор наоборот исключает колонки с выбора. Другие все выбираются!
    public static ReportSetting getTotalServiceFees() {
        return new ReportSetting(
                "Servicegebühren",
                Map.of(
                        COLUMN_TYP, TYP_SERVICEGEBUEHR,
                        COLUMN_BESCHREIBUNG, EXCLUDE_WERBEKOSTEN  // Исключающий фильтр
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalStorageAndServiceFeesForAmazonFulfillment() {
        return new ReportSetting(
                "Lagerbestands- und Service-Gebühren für Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_LAGERGEBUEHR
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalRefundsForShippingCredits() {
        return new ReportSetting(
                "Erstattungen für Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }

    public static ReportSetting getTotalRefundsForPromotionalDiscounts() {
        return new ReportSetting(
                "Erstattungen zur Werbeaktionsrabatt",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalShippingCreditNotes() {
        return new ReportSetting(
                "Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }

    // Описан случай, где выбор наоборот исключает колонки с выбора. Другие все выбираются!
    public static ReportSetting getTotalFBALogisticsInventoryCredits() {
        return new ReportSetting(
                "FBA Lagerbestandsguthaben",
                Map.of(
                        COLUMN_TYP, TYP_ANPASSUNG,
                        COLUMN_BESCHREIBUNG, EXCLUDE_ALLGEMEINE_ANPASSUNG  // Исключающий фильтр
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalRefundsForAmazonShippedItems() {
        return new ReportSetting(
                "Erstattungen für durch Amazon versandte Artikel",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalRefundsForAmazonTransactionFees() {
        return new ReportSetting(
                "Ersattungen zur Transaktionsgebühr - Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalRefundAmountForReturnedShipments() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG

                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getUniqueValuesFromFilteredColumn() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG

                ),

                UNIQUE_LIST_BESTELLNUMMER
        );
    }

    //Tests
    public static ReportSetting getTotalRefundAmountForReturnedShipments2() {
        return new ReportSetting(
                "TestIT",
                Map.of(
                        COLUMN_BESTELLUNG,List.of("028-0001650-0973127")

                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }



}
