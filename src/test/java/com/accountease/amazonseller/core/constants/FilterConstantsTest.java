package com.accountease.amazonseller.core.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Epic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Validation of FilterConstants for correctness and consistency")
@DisplayName("Filter Constants Test Suite")
public class FilterConstantsTest {

    @Test
    @DisplayName("Verify DATE_COLUMN_EXCEL constant")
    void testDateColumnExcel() {
        assertEquals("Datum/Uhrzeit", FilterConstants.DATE_COLUMN_EXCEL,
                "FilterConstants.DATE_COLUMN_EXCEL does not match the expected value.");
    }

    @Test
    @DisplayName("Verify COLUMN_TYP constant")
    void testColumnTyp() {
        assertEquals("Typ", FilterConstants.COLUMN_TYP,
                "FilterConstants.COLUMN_TYP does not match the expected value.");
    }

    @Test
    @DisplayName("Verify COLUMN_VERSAND constant")
    void testColumnVersand() {
        assertEquals("Versand", FilterConstants.COLUMN_VERSAND,
                "FilterConstants.COLUMN_VERSAND does not match the expected value.");
    }

    @Test
    @DisplayName("Verify COLUMN_BESCHREIBUNG constant")
    void testColumnBeschreibung() {
        assertEquals("Beschreibung", FilterConstants.COLUMN_BESCHREIBUNG,
                "FilterConstants.COLUMN_BESCHREIBUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify COLUMN_BESTELLNUMMER constant")
    void testColumnBestellnummer() {
        assertEquals("Bestellnummer", FilterConstants.COLUMN_BESTELLNUMMER,
                "FilterConstants.COLUMN_BESTELLNUMMER does not match the expected value.");
    }

    @Test
    @DisplayName("Verify TYP_BESTELLUNG constant")
    void testTypBestellung() {
        assertEquals(List.of("Bestellung"), FilterConstants.TYP_BESTELLUNG,
                "FilterConstants.TYP_BESTELLUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify TYP_SERVICEGEBUEHR constant")
    void testTypServicegebuehr() {
        assertEquals(List.of("Servicegebühr"), FilterConstants.TYP_SERVICEGEBUEHR,
                "FilterConstants.TYP_SERVICEGEBUEHR does not match the expected value.");
    }

    @Test
    @DisplayName("Verify TYP_ANPASSUNG constant")
    void testTypAnpassung() {
        assertEquals(List.of("Anpassung"), FilterConstants.TYP_ANPASSUNG,
                "FilterConstants.TYP_ANPASSUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify TYP_LAGERGEBUEHREN constant")
    void testTypLagergebuehren() {
        assertEquals(List.of("Versand durch Amazon Lagergebühr"), FilterConstants.TYP_LAGERGEBUEHREN,
                "FilterConstants.TYP_LAGERGEBUEHREN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify TYP_ERSTATTUNG constant")
    void testTypErstattung() {
        assertEquals(List.of("Erstattung"), FilterConstants.TYP_ERSTATTUNG,
                "FilterConstants.TYP_ERSTATTUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify VERSAND_VERKAEUFER constant")
    void testVersandVerkaeufer() {
        assertEquals(List.of("Verkäufer"), FilterConstants.VERSAND_VERKAEUFER,
                "FilterConstants.VERSAND_VERKAEUFER does not match the expected value.");
    }

    @Test
    @DisplayName("Verify VERSAND_AMAZON constant")
    void testVersandAmazon() {
        assertEquals(List.of("Amazon"), FilterConstants.VERSAND_AMAZON,
                "FilterConstants.VERSAND_AMAZON does not match the expected value.");
    }

    @Test
    @DisplayName("Verify BESCHREIBUNG_WERBEKOSTEN constant")
    void testBeschreibungWerbekosten() {
        assertEquals(List.of("Werbekosten"), FilterConstants.BESCHREIBUNG_WERBEKOSTEN,
                "FilterConstants.BESCHREIBUNG_WERBEKOSTEN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG constant")
    void testBeschreibungFBAErstattungLagerbestandAnpassung() {
        assertEquals(List.of("Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung"),
                FilterConstants.BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG,
                "FilterConstants.BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify EXCLUDE_WERBEKOSTEN constant")
    void testExcludeWerbekosten() {
        assertEquals(List.of("!exclude", "Werbekosten"), FilterConstants.EXCLUDE_WERBEKOSTEN,
                "FilterConstants.EXCLUDE_WERBEKOSTEN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify EXCLUDE_ALLGEMEINE_ANPASSUNG constant")
    void testExcludeAllgemeineAnpassung() {
        assertEquals(List.of("!exclude",
                        "Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung",
                        "Versand durch Amazon Erstattung für Lagerbestand - Kundenrücksendung"),
                FilterConstants.EXCLUDE_ALLGEMEINE_ANPASSUNG,
                "FilterConstants.EXCLUDE_ALLGEMEINE_ANPASSUNG does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_VERKAUFSGEBUEHREN constant")
    void testNumericVerkaufsgebuehren() {
        assertEquals(List.of("Verkaufsgebühren"), FilterConstants.NUMERIC_VERKAUFSGEBUEHREN,
                "FilterConstants.NUMERIC_VERKAUFSGEBUEHREN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_UMSAETZE constant")
    void testNumericUmsaetze() {
        assertEquals(List.of("Umsätze"), FilterConstants.NUMERIC_UMSAETZE,
                "FilterConstants.NUMERIC_UMSAETZE does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_GESAMT constant")
    void testNumericGesamt() {
        assertEquals(List.of("Gesamt"), FilterConstants.NUMERIC_GESAMT,
                "FilterConstants.NUMERIC_GESAMT does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_GEBUEHREN_VERSAND_AMAZON constant")
    void testNumericGebuehrenVersandAmazon() {
        assertEquals(List.of("Gebühren zu Versand durch Amazon"), FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON,
                "FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_RABATTE_AUS_WERBEAKTIONEN constant")
    void testNumericRabatteAusWerbeaktionen() {
        assertEquals(List.of("Rabatte aus Werbeaktionen"), FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN,
                "FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN constant")
    void testNumericGutschriftFuerVersandkosten() {
        assertEquals(List.of("Gutschrift für Versandkosten"), FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN,
                "FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN does not match the expected value.");
    }

    @Test
    @DisplayName("Verify UNIQUE_LIST_BESTELLNUMMER constant")
    void testUniqueListBestellnummer() {
        assertEquals(List.of("Bestellnummer"), FilterConstants.UNIQUE_LIST_BESTELLNUMMER,
                "FilterConstants.UNIQUE_LIST_BESTELLNUMMER does not match the expected value.");
    }
}
