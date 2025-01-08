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
}
