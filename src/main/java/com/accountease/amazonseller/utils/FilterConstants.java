package com.accountease.amazonseller.utils;

import java.util.List;

/**
 * Класс для хранения констант, используемых в фильтрах.
 *
 * Описание:
 * - Содержит названия колонок, уникальные значения, списки исключений и другие параметры,
 *   которые используются в методах фильтрации.
 * - Помогает централизованно управлять всеми параметрами, избегая дублирования.
 */
public class FilterConstants {

    // Названия колонок
    public static final String COLUMN_TYP = "Typ";
    public static final String COLUMN_VERSAND = "Versand";
    public static final String COLUMN_BESCHREIBUNG = "Beschreibung";
    public static final String COLUMN_BESTELLNUMMER = "Bestellnummer";

    // Уникальные значения колонок
    public static final List<String> TYP_BESTELLUNG = List.of("Bestellung");
    public static final List<String> TYP_SERVICEGEBUEHR = List.of("Servicegebühr");
    public static final List<String> TYP_ANPASSUNG = List.of("Anpassung");
    public static final List<String> TYP_LAGERGEBUEHREN = List.of("Versand durch Amazon Lagergebühr");
    public static final List<String> TYP_ERSTATTUNG = List.of("Erstattung");

    public static final List<String> VERSAND_VERKAEUFER = List.of("Verkäufer");
    public static final List<String> VERSAND_AMAZON = List.of("Amazon");

    public static final List<String> BESCHREIBUNG_WERBEKOSTEN = List.of("Werbekosten");
    public static final List<String> BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG =
            List.of("Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung");

    // ❗ Особые случаи: исключающие фильтры
    public static final List<String> EXCLUDE_WERBEKOSTEN = List.of("!exclude", "Werbekosten");
    public static final List<String> EXCLUDE_ALLGEMEINE_ANPASSUNG = List.of(
            "!exclude",
            "Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung",
            "Versand durch Amazon Erstattung für Lagerbestand - Kundenrücksendung"
    );

    // Названия cуммарных итогов для вывода
    public static final List<String> NUMERIC_VERKAUFSGEBUEHREN = List.of("Verkaufsgebühren");
    public static final List<String> NUMERIC_UMSAETZE = List.of("Umsätze");
    public static final List<String> NUMERIC_GESAMT = List.of("Gesamt");
    public static final List<String> NUMERIC_GEBUEHREN_VERSAND_AMAZON = List.of("Gebühren zu Versand durch Amazon");
    public static final List<String> NUMERIC_RABATTE_AUS_WERBEAKTIONEN = List.of("Rabatte aus Werbeaktionen");
    public static final List<String> NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN = List.of("Gutschrift für Versandkosten");

    // Параметры для вывода уникальных колонок
    public static final List<String> UNIQUE_LIST_BESTELLNUMMER = List.of("Bestellnummer");
}
