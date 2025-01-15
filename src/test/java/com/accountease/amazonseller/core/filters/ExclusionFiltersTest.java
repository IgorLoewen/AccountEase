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
 * Unit tests for the ExclusionFilters class.
 *
 * These tests ensure the correctness of the parameters provided by the ExclusionFilters methods
 * (e.g., getTotalServiceFees, getTotalFBALogisticsInventoryCredits). They validate that the returned
 * {@link ReportSetting} objects contain the expected values for the name, column filters, and numeric columns.
 *
 * The primary purpose of these tests is to ensure that the filters contain strictly defined values,
 * which are critical to the functionality of the program's business logic. Any change to the filter parameters,
 * such as names, column filter values, or numeric columns, will cause the program to malfunction. This is
 * because the filters determine the key aspects of report generation, data filtering, and business operations.
 *
 * Although the actual values for {@link FilterConstants} are tested separately in {@link FilterConstantsTest},
 * these tests validate their correct integration and usage within the ExclusionFilters methods. This ensures
 * the tests are isolated and specifically verify the filter configuration in this class.
 *
 * Any changes in the filter logic (e.g., value modifications, data structure updates, or business rules)
 * will cause these tests to fail immediately, helping identify and address issues related to the
 * ExclusionFilters configuration. These tests are critical for maintaining the program's stability
 * and predictable behavior.
 */

@Epic("Validation of Filter Parameters for Accuracy and Correctness")
@DisplayName("ExclusionFiltersTest")
class ExclusionFiltersTest {

    private static Stream<Object[]> provideFilterData() {
        return Stream.of(
                new Object[]{
                        "Servicegebühren", // name
                        Map.of( // columnFilters
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_SERVICEGEBUEHR,
                                FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.EXCLUDE_WERBEKOSTEN
                        ),
                        FilterConstants.NUMERIC_GESAMT, // numericColumns
                        ExclusionFilters.getTotalServiceFees() // actual filter
                },
                new Object[]{
                        "FBA Lagerbestandsguthaben", // name
                        Map.of(
                                FilterConstants.COLUMN_TYP, FilterConstants.TYP_ANPASSUNG,
                                FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.EXCLUDE_ALLGEMEINE_ANPASSUNG
                        ),
                        FilterConstants.NUMERIC_GESAMT,
                        ExclusionFilters.getTotalFBALogisticsInventoryCredits()
                }
        );
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify name in ExclusionFilters")
    void testNameFromExclusionFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {

        assertEquals(expectedName, actualFilter.getName(), "Filter name does not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify column filters in ExclusionFilters")
    void testColumnFiltersFromExclusionFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {

        assertEquals(expectedColumnFilters, actualFilter.getColumnFilters(), "Column filters do not match");
    }

    @ParameterizedTest
    @MethodSource("provideFilterData")
    @DisplayName("Verify numeric columns in ExclusionFilters")
    void testNumericColumnsFromExclusionFilters(String expectedName, Map<String, List<String>> expectedColumnFilters, List<String> expectedNumericColumns, ReportSetting actualFilter) {

        assertEquals(expectedNumericColumns, actualFilter.getNumericColumns(), "Numeric columns do not match");
    }
}
