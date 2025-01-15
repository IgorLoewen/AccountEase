package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the ValueListFilters class.
 *
 * These tests ensure the correctness of the parameters provided by the ValueListFilters methods
 * (e.g., getUniqueValuesFromFilteredColumn, buildFilterFromUniqueColumnValues). They validate that
 * the returned {@link ReportSetting} objects contain the expected values for the name, column filters,
 * and numeric columns or unique values.
 *
 * These tests are critical to ensure that the filters provide consistent and expected configurations
 * for the program's functionality. Any changes to the filter parameters will cause these tests to fail,
 * ensuring issues are identified early.
 */
@Epic("Validation of Filter Parameters for Accuracy and Correctness")
@DisplayName("ValueListFiltersTest")
class ValueListFiltersTest {

    private static Stream<Object[]> provideFilterData() {
        return Stream.of(
                new Object[]{
                        "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                        ),
                        FilterConstants.UNIQUE_LIST_BESTELLNUMMER,
                        ValueListFilters.getUniqueValuesFromFilteredColumn()
                },
                new Object[]{
                        "Bearbeitungsgebühren für Erstattungen",
                        Map.of(
                                FilterConstants.COLUMN_BESTELLNUMMER, List.of("value1", "value2")
                        ),
                        FilterConstants.NUMERIC_VERKAUFSGEBUEHREN,
                        ValueListFilters.buildFilterFromUniqueColumnValues(List.of("value1", "value2"))
                }
        );
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify name in ValueListFilters")
    void testNameFromValueListFilters(String expectedName, Map<String, ?> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedName, actualFilter.getName(), "Filter name does not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify column filters in ValueListFilters")
    void testColumnFiltersFromValueListFilters(String expectedName, Map<String, ?> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedColumnFilters, actualFilter.getColumnFilters(), "Column filters do not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify numeric columns or unique values in ValueListFilters")
    void testNumericColumnsFromValueListFilters(String expectedName, Map<String, ?> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {
        assertEquals(expectedNumericColumns, actualFilter.getNumericColumns(), "Numeric columns or unique values do not match");
    }
}
