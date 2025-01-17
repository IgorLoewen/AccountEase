package com.accountease.amazonseller.core.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Epic;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class MultiColumnFilterTest.
 *
 * This class contains unit tests for the MultiColumnFilter class, which filters data based on multiple column filters.
 *
 * Why this is important:
 * - MultiColumnFilter is a critical component of the data filtering system.
 * - These tests ensure that changes in logic or code do not break its functionality.
 * - The tests cover both standard scenarios (filtering by single or multiple columns) and edge cases
 *   (empty data, missing columns, `null` values).
 * - In addition to testing the public method `filterByColumns`, these tests indirectly verify the behavior of
 *   the private method `filterBySingleColumn`, which handles filtering for individual columns.
 *
 * Key Tests:
 *
 * 1. **testFilterByColumnsSingleColumnRowCount**
 *    Verifies that filtering by a single column returns the correct number of rows.
 *
 * 2. **testFilterByColumnsSingleColumnFirstRow**
 *    Verifies that the first row in the result after filtering by a single column contains the expected value.
 *
 * 3. **testFilterByColumnsSingleColumnSecondRow**
 *    Verifies the second row in the result after filtering by a single column.
 *
 * 4. **testFilterByColumnsMultipleColumnsRowCount**
 *    Verifies that filtering by multiple columns returns only rows matching all conditions.
 *
 * 5. **testFilterByColumnsMultipleColumnsContentColumnA**
 *    Verifies the value in `testColumnA` after filtering by multiple columns.
 *
 * 6. **testFilterByColumnsMultipleColumnsContentColumnB**
 *    Verifies the value in `testColumnB` after filtering by multiple columns.
 *
 * 7. **testFilterByColumnsEmptyFiltersRowCount**
 *    Verifies that when filters are empty, the method returns all rows.
 *
 * 8. **testFilterByColumnsEmptyDataRowCount**
 *    Verifies that when the input data is empty, the method returns an empty result.
 *
 * 9. **testFilterByColumnsMissingColumnRowCount**
 *    Verifies that when a column specified in the filters is missing from the data, the method returns an empty result.
 *
 * 10. **testFilterByColumnsNullColumnFilters**
 *     Verifies that when `columnFilters` is `null`, the method returns all rows.
 *
 * 11. **testFilterByColumnsNullData**
 *     Verifies that when the input data (`data`) is `null`, the method returns an empty result.
 *
 * 12. **testFilterByColumnsEmptyColumnFilters**
 *     Verifies that when the filters are empty, the method returns all rows, similar to having no filters.
 *
 * 13. **testFilterByColumnsEmptyData**
 *     Verifies that when the input data is empty, the method handles this case correctly and returns an empty result.
 *
 * These tests validate both the primary use cases and edge cases. This approach ensures the stability and predictability
 * of the MultiColumnFilter class, even when the logic is modified.
 */

@Tag("unit")
@Epic("Multi-Column Filtering")
@DisplayName("MultiColumnFilterTest")
class MultiColumnFilterTest {

    private final MultiColumnFilter multiColumnFilter = new MultiColumnFilter();

    private List<Map<String, String>> testData;

    @BeforeEach
    void setUp() {
        testData = List.of(
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue2"),
                Map.of("testColumnA", "testValue3", "testColumnB", "testValue4"),
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue3"),
                Map.of("testColumnA", "testValue3", "testColumnB", "testValue2")
        );
    }

    @Test
    @DisplayName("Filter by single column - Validate number of rows")
    void testFilterByColumnsSingleColumnRowCount() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals(2, result.size(), "Expected 2 rows to match the filter for testColumnA = testValue1.");
    }

    @Test
    @DisplayName("Filter by single column - Validate first row")
    void testFilterByColumnsSingleColumnFirstRow() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals("testValue1", result.get(0).get("testColumnA"), "First row's testColumnA should be testValue1.");
    }

    @Test
    @DisplayName("Filter by single column - Validate second row")
    void testFilterByColumnsSingleColumnSecondRow() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals("testValue1", result.get(1).get("testColumnA"), "Second row's testColumnA should be testValue1.");
    }

    @Test
    @DisplayName("Filter by multiple columns - Validate number of rows")
    void testFilterByColumnsMultipleColumnsRowCount() {
        Map<String, List<String>> columnFilters = Map.of(
                "testColumnA", List.of("testValue1"),
                "testColumnB", List.of("testValue2")
        );

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals(1, result.size(), "Expected 1 row to match the filters for testColumnA = testValue1 and testColumnB = testValue2.");
    }

    @Test
    @DisplayName("Filter by multiple columns - Validate testColumnA content")
    void testFilterByColumnsMultipleColumnsContentColumnA() {
        Map<String, List<String>> columnFilters = Map.of(
                "testColumnA", List.of("testValue1"),
                "testColumnB", List.of("testValue2")
        );

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals("testValue1", result.get(0).get("testColumnA"), "First row's testColumnA should be testValue1.");
    }

    @Test
    @DisplayName("Filter by multiple columns - Validate testColumnB content")
    void testFilterByColumnsMultipleColumnsContentColumnB() {
        Map<String, List<String>> columnFilters = Map.of(
                "testColumnA", List.of("testValue1"),
                "testColumnB", List.of("testValue2")
        );

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals("testValue2", result.get(0).get("testColumnB"), "First row's testColumnB should be testValue2.");
    }

    @Test
    @DisplayName("Filter with empty filters - Validate row count")
    void testFilterByColumnsEmptyFiltersRowCount() {
        Map<String, List<String>> columnFilters = Map.of();

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals(4, result.size(), "All rows should be included when filters are empty.");
    }

    @Test
    @DisplayName("Filter with empty data - Validate row count")
    void testFilterByColumnsEmptyDataRowCount() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));
        List<Map<String, String>> emptyData = List.of();

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, emptyData);

        assertTrue(result.isEmpty(), "No rows should match when data is empty.");
    }

    @Test
    @DisplayName("Filter with missing column - Validate row count")
    void testFilterByColumnsMissingColumnRowCount() {
        Map<String, List<String>> columnFilters = Map.of("testColumnC", List.of("testValue1"));

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertTrue(result.isEmpty(), "No rows should match when the column is missing.");
    }

    @Test
    @DisplayName("Validate handling of null columnFilters")
    void testFilterByColumnsNullColumnFilters() {
        Map<String, List<String>> columnFilters = null;

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals(4, result.size(), "All rows should be included when columnFilters is null.");
    }

    @Test
    @DisplayName("Validate handling of null data")
    void testFilterByColumnsNullData() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));
        List<Map<String, String>> data = null;

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, data);

        assertTrue(result.isEmpty(), "Result should be empty when data is null.");
    }

    @Test
    @DisplayName("Validate handling of empty columnFilters")
    void testFilterByColumnsEmptyColumnFilters() {
        Map<String, List<String>> columnFilters = Map.of();

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, testData);

        assertEquals(4, result.size(), "All rows should be included when columnFilters is empty.");
    }

    @Test
    @DisplayName("Validate handling of empty data")
    void testFilterByColumnsEmptyData() {
        Map<String, List<String>> columnFilters = Map.of("testColumnA", List.of("testValue1"));
        List<Map<String, String>> data = List.of();

        List<Map<String, String>> result = multiColumnFilter.filterByColumns(columnFilters, data);

        assertTrue(result.isEmpty(), "Result should be empty when data is empty.");
    }
}
