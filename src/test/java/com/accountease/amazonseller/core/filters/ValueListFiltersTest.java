package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;
import com.accountease.amazonseller.core.constants.FilterConstantsTest;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
 * The primary purpose of these tests is to verify that the filters provide consistent and expected configurations,
 * which are critical for the program's business logic. Any changes to the filter parameters, such as names,
 * column filter values, or unique lists, will cause these tests to fail. This ensures that issues are detected early
 * and resolved before affecting the functionality.
 *
 * While the actual parameter values are validated separately in {@link FilterConstantsTest}, these tests
 * focus on their proper integration and usage within the methods of the ValueListFilters class. The tests are
 * isolated and specifically validate the dynamic filter configurations created by this class.
 *
 * These tests are parameterized to verify multiple scenarios efficiently. Each parameterized test evaluates
 * a specific aspect of the filter (e.g., name, column filters, numeric columns, or unique values), ensuring
 * one assert per test for easier debugging. This design helps pinpoint the exact cause of a failure and facilitates
 * maintaining the stability and predictability of the program.
 *
 * Any changes to the filter logic (e.g., value modifications, data structure updates, or business rules) will
 * immediately trigger failures in these tests. This ensures that the ValueListFilters class remains reliable and
 * functions as intended within the broader system.
 */
@Tag("unit")
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
