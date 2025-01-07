package com.accountease.amazonseller.core;

import java.util.List;
import java.util.Map;

public class ReportSettingsFactory {

    public static ReportSetting createSalesReport() {
        return new ReportSetting(
                "Sales Report",
                Map.of(
                        "Typ", List.of("Bestellung"),
                        "Versand", List.of("Verkäufer")
                ),
                List.of("Umsätze", "Verkaufsgebühren")
        );
    }

    public static ReportSetting createExpenseReport() {
        return new ReportSetting(
                "Expense Report",
                Map.of(
                        "Typ", List.of("Erstattung"),
                        "Beschreibung", List.of("Beitrag")
                ),
                List.of("Andere", "Steuer auf Aktionsrabatte")
        );
    }
}
