package com.accountease.amazonseller.core;

import java.util.List;
import java.util.Map;

public class ReportSettingsFactory {

    public static ReportSetting createSellerShippingFeeReport() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Verkäufer",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of("Verkäufer")
                ),
                List.of("Verkaufsgebühren")
        );
    }

    public static ReportSetting createAmazonShippingFeeReport() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Amazon",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of("Amazon")
                ),
                List.of("Verkaufsgebühren")
        );
    }

    public static ReportSetting createSalesReportSeller() {
        return new ReportSetting(
                "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of("Verkäufer")
                ),
                List.of("Umsätze")
        );
    }

    public static ReportSetting createSalesReportAmazon() {
        return new ReportSetting(
                "Verkäufe mit Versand durch Amazon",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of("Amazon")
                ),
                List.of("Umsätze")
        );
    }

    public static ReportSetting createAdvertisingCostsReport() {
        return new ReportSetting(
                "Werbekosten",
                Map.of(
                        "Typ", List.of("Servicegebühr"),
                        "Beschreibung", List.of("Werbekosten")
                ),
                List.of("Gesamt")
        );
    }

    public static ReportSetting createAmazonFulfillmentFees() {
        return new ReportSetting(
                "Transaktionsgebühren Versand durch Amazon",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of()
                ),
                List.of("Gebühren zu Versand durch Amazon")
        );
    }


}
