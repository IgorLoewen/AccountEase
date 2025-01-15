package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;
import com.accountease.amazonseller.core.constants.FilterConstantsTest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Юнит-тесты для класса StandardFilters.
 *
 * Эти тесты проверяют корректность параметров, предоставляемых методами класса StandardFilters
 * (например, getTotalSellerShippingFee, getTotalAmazonShippingFee и другими). Они проверяют, что
 * возвращаемые объекты {@link ReportSetting} содержат ожидаемые значения для имени, фильтров колонок
 * и числовых колонок.
 *
 * Основная цель этих тестов — гарантировать, что фильтры содержат строго определённые значения,
 * которые являются критически важными для бизнес-логики программы. Любые изменения параметров фильтров,
 * такие как имена, значения фильтров колонок или числовые колонки, приведут к сбоям в работе программы.
 * Это обусловлено тем, что фильтры определяют ключевые аспекты генерации отчётов, фильтрации данных
 * и бизнес-процессов.
 *
 * Хотя фактические значения параметров проверяются отдельно в {@link FilterConstantsTest}, эти тесты
 * валидируют их правильную интеграцию и использование в методах класса StandardFilters. Это обеспечивает
 * изолированность тестов и точную проверку конфигурации фильтров в этом классе.
 *
 * Любые изменения в логике фильтров (например, модификация значений, обновление структуры данных или
 * бизнес-правил) немедленно вызовут сбои в этих тестах, что поможет быстро выявить и устранить проблемы,
 * связанные с конфигурацией фильтров в StandardFilters. Эти тесты являются критически важными для
 * поддержания стабильности и предсказуемого поведения программы.
 */


@Epic("Validation of Filter Parameters for Accuracy and Correctness")
@DisplayName("StandardFiltersTest")
class StandardFiltersTest {

    private static Stream<Object[]> provideFilterData() {
        return Stream.of(
                new Object[]{
                        "Verkaufsgebühren Versand durch Verkäufer", // name
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                                FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_VERKAEUFER
                        ),
                        FilterConstants.NUMERIC_VERKAUFSGEBUEHREN,
                        StandardFilters.getTotalSellerShippingFee() // actual filter
                },
                new Object[]{
                        "Verkaufsgebühren Versand durch Amazon",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                                FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_AMAZON
                        ),
                        FilterConstants.NUMERIC_VERKAUFSGEBUEHREN,
                        StandardFilters.getTotalAmazonShippingFee()
                },
                new Object[]{
                        "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                                FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_VERKAEUFER
                        ),
                        FilterConstants.NUMERIC_UMSAETZE,
                        StandardFilters.getTotalSalesSumSeller()
                },
                new Object[]{
                        "Verkäufe mit Versand durch Amazon",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                                FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_AMAZON
                        ),
                        FilterConstants.NUMERIC_UMSAETZE,
                        StandardFilters.getTotalSalesSumAmazon()
                },
                new Object[]{
                        "Werbekosten",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_SERVICEGEBUEHR,
                                FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.BESCHREIBUNG_WERBEKOSTEN
                        ),
                        FilterConstants.NUMERIC_GESAMT,
                        StandardFilters.getTotalAdvertisingCosts()
                },
                new Object[]{
                        "Transaktionsgebühren Versand durch Amazon",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                        ),
                        FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON,
                        StandardFilters.getTotalAmazonFulfillmentFees()
                },
                new Object[]{
                        "Werbeaktionsrabatte",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                        ),
                        FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN,
                        StandardFilters.getTotalPromotionalDiscountsFees()
                },
                new Object[]{
                        "Anpassungen",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ANPASSUNG,
                                FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG
                        ),
                        FilterConstants.NUMERIC_GESAMT,
                        StandardFilters.getTotalAdjustmentsFees()
                },
                new Object[]{
                        "Lagerbestands- und Service-Gebühren für Versand durch Amazon",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_LAGERGEBUEHREN
                        ),
                        FilterConstants.NUMERIC_GESAMT,
                        StandardFilters.getTotalStorageAndServiceFeesForAmazonFulfillment()
                },
                new Object[]{
                        "Erstattungen für Versandkostengutschriften",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                        ),
                        FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN,
                        StandardFilters.getTotalRefundsForShippingCredits()
                },
                new Object[]{
                        "Erstattungen zur Werbeaktionsrabatt",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                        ),
                        FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN,
                        StandardFilters.getTotalRefundsForPromotionalDiscounts()
                },
                new Object[]{
                        "Versandkostengutschriften",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                        ),
                        FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN,
                        StandardFilters.getTotalShippingCreditNotes()
                },
                new Object[]{
                        "Erstattungen für durch Amazon versandte Artikel",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                        ),
                        FilterConstants.NUMERIC_UMSAETZE,
                        StandardFilters.getTotalRefundsForAmazonShippedItems()
                },
                new Object[]{
                        "Ersattungen zur Transaktionsgebühr - Versand durch Amazon",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                        ),
                        FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON,
                        StandardFilters.getTotalRefundsForAmazonTransactionFees()
                },
                new Object[]{
                        "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG

                        ),
                        FilterConstants.NUMERIC_VERKAUFSGEBUEHREN,
                        StandardFilters.getTotalRefundAmountForReturnedShipments()
                }
        );
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify name in StandardFilters")
    void testNameFromStandardFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedName, actualFilter.getName(), "Filter name does not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify column filters in StandardFilters")
    void testColumnFiltersFromStandardFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedColumnFilters, actualFilter.getColumnFilters(), "Column filters do not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify numeric columns in StandardFilters")
    void testNumericColumnsFromStandardFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedNumericColumns, actualFilter.getNumericColumns(), "Numeric columns do not match");
    }
}
