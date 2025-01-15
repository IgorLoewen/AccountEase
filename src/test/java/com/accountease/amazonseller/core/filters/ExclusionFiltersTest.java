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
