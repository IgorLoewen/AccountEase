package com.accountease.amazonseller.core.constants;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The class for storing constants used in filters.
 *
 * Description:
 * - Contains column names, unique values, exclusion lists, and other parameters
 *   used in filtering methods.
 * - The variable names and their values are critically important for the correct
 *   operation of the filters. Any changes can affect the business logic and
 *   compromise the system's stability.
 * - Facilitates centralized management of all parameters, avoiding duplication
 *   and simplifying the development process.
 * - At this stage, explicit variable names enhance the understanding of the
 *   project structure and make working with the code easier.
 * - In the future, for scaling the project, it will be possible to add row
 *   indexing to use functional templates for all languages, improving the
 *   adaptability and extensibility of the system.
 */
public class FilterConstants {

    // Параметры Excel
    public static final String FILE_PATH = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx";
    public static final int HEADER_ROW_INDEX = 7; // Строка заголовков

    // Даты для фильтрации
    public static final String START_DATE = "01.07.2024 00:00:00";
    public static final String END_DATE = "31.12.2024 23:59:59";

    // Формат даты
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    // Колонка для даты в Excel
    public static final String DATE_COLUMN_EXCEL = "Datum/Uhrzeit";


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
