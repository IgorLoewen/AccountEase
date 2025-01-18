package com.accountease.amazonseller.core.processor;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating the methods of the UniqueValuesProcessor class.
 *
 * This class includes:
 * - Tests for the getUniqueValues method.
 * - Tests for the getLastNumericColumnName method.
 * - Tests for the extractUniqueValuesFromLastNumericColumn method.
 *
 * The tests include:
 * - Isolated unit tests: These verify the functionality of individual methods
 *   without relying on interactions with other methods or external dependencies.
 * - Non-isolated unit tests: These test the integration of multiple methods within
 *   the class, ensuring that they work together correctly to produce the expected results.
 *
 * The reason for including non-isolated tests is that some methods, like
 * extractUniqueValuesFromLastNumericColumn, rely on other methods (e.g.,
 * getLastNumericColumnName, getUniqueValues, and MultiColumnFilter) to perform their tasks.
 * Testing these methods in isolation wouldn't sufficiently validate their functionality
 * in the context of real-world usage scenarios.
 *
 * The tests verify:
 * - Correct behavior with different input scenarios.
 * - Robustness against invalid inputs (e.g., null, empty values).
 * - Compliance with business logic, such as maintaining insertion order and
 *   handling case sensitivity where required.
 */

@Epic("Validation and Extraction of Unique Values")
@DisplayName("UniqueValuesProcessor")
class UniqueValuesProcessorTest {

    private final UniqueValuesProcessor processor = new UniqueValuesProcessor();

    /**
     * Tests for the getUniqueValues method.
     *
     * Test scenarios:
     * 1. **Normal case**:
     *    Verify extraction of unique values from a specified column when no duplicates exist.
     * 2. **Duplicates**:
     *    Ensure duplicate values are removed from the result.
     * 3. **Empty strings**:
     *    Validate that empty or whitespace-only strings are ignored.
     * 4. **Missing column**:
     *    Check that an empty list is returned if the specified column does not exist.
     * 5. **Empty data**:
     *    Confirm that an empty list is returned if the input data is empty.
     * 6. **Case sensitivity**:
     *    Verify that "testValue" and "TestValue" are treated as different values.
     * 7. **Invalid inputs**:
     *    Ensure the method throws IllegalArgumentException when the input data or column name is null.
     */
    @Tag("unit")
    @DisplayName("Verify unique value extraction when no duplicates exist")
    @Test
    void testGetUniqueValues_NormalCase() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue2"),
                Map.of("testColumnA", "testValue2", "testColumnB", "testValue3"),
                Map.of("testColumnA", "testValue3", "testColumnB", "testValue4")
        );
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertEquals(List.of("testValue1", "testValue2", "testValue3"), result);
    }

    @Tag("unit")
    @DisplayName("Verify duplicate values are removed and only unique values are returned")
    @Test
    void testGetUniqueValues_Duplicates() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue2"),
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue3")
        );
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertEquals(List.of("testValue1"), result);
    }

    @Tag("unit")
    @DisplayName("Verify empty or whitespace-only strings are ignored")
    @Test
    void testGetUniqueValues_EmptyStrings() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnA", " ", "testColumnB", "testValue2"),
                Map.of("testColumnA", "", "testColumnB", "testValue3")
        );
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertTrue(result.isEmpty());
    }

    @Tag("unit")
    @DisplayName("Verify empty result is returned when the specified column is missing")
    @Test
    void testGetUniqueValues_MissingColumn() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnB", "testValue2"),
                Map.of("testColumnB", "testValue3")
        );
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertTrue(result.isEmpty());
    }

    @Tag("unit")
    @DisplayName("Verify empty result is returned when input data is empty")
    @Test
    void testGetUniqueValues_EmptyData() {
        List<Map<String, String>> data = Collections.emptyList();
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertTrue(result.isEmpty());
    }

    @Tag("unit")
    @DisplayName("Verify case sensitivity by treating 'testValue' and 'TestValue' as distinct")
    @Test
    void testGetUniqueValues_CaseSensitivity() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnA", "testValue", "testColumnB", "testValue2"),
                Map.of("testColumnA", "TestValue", "testColumnB", "testValue3")
        );
        List<String> result = processor.getUniqueValues(data, "testColumnA");
        assertEquals(List.of("testValue", "TestValue"), result);
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when input data is null")
    @Test
    void testGetUniqueValues_NullData() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.getUniqueValues(null, "testColumnA");
        });
        assertEquals("Data cannot be null.", exception.getMessage());
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when column name is null")
    @Test
    void testGetUniqueValues_NullColumn() {
        List<Map<String, String>> data = List.of(
                Map.of("testColumnA", "testValue1", "testColumnB", "testValue2")
        );
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.getUniqueValues(data, null);
        });
        assertEquals("Column name cannot be null.", exception.getMessage());
    }


    /**
     * Tests for the getLastNumericColumnName method.
     *
     * Test scenarios:
     * 1. **Normal case**:
     *    Verifies that the last column name is returned from a list with multiple elements.
     * 2. **Single element**:
     *    Verifies that the single column name is returned when the list contains only one element.
     * 3. **Empty list**:
     *    Ensures that an IllegalStateException is thrown when the list is empty.
     * 4. **Null list**:
     *    Ensures that an IllegalStateException is thrown when the list is null.
     * 5. **Boundary cases**:
     *    Verifies the method works correctly with column names containing spaces, numbers, or special characters.
     */
    @Tag("unit")
    @DisplayName("Verify the last numeric column is returned from a list of multiple elements")
    @Test
    void testGetLastNumericColumnName_NormalCase() {
        List<String> numericColumns = List.of("TestColumn1", "TestColumn2", "TestColumn3");
        String result = processor.getLastNumericColumnName(numericColumns);
        assertEquals("TestColumn3", result);
    }

    @Tag("unit")
    @DisplayName("Verify the single numeric column is returned when the list contains one element")
    @Test
    void testGetLastNumericColumnName_SingleElement() {
        List<String> numericColumns = List.of("TestColumn1");
        String result = processor.getLastNumericColumnName(numericColumns);
        assertEquals("TestColumn1", result);
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when the numeric columns list is empty")
    @Test
    void testGetLastNumericColumnName_EmptyList() {
        List<String> numericColumns = Collections.emptyList();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            processor.getLastNumericColumnName(numericColumns);
        });
        assertEquals("The list of numeric columns is empty or null.", exception.getMessage());
    }

    @Tag("unit")
    @Test
    void testGetLastNumericColumnName_NullList() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            processor.getLastNumericColumnName(null);
        });
        assertEquals("The list of numeric columns is empty or null.", exception.getMessage());
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when the numeric columns list is null")
    @Test
    void testGetLastNumericColumnName_BoundaryCases() {
        List<String> numericColumns = List.of("Test123", "Test$Column", " TestColumn3 ");
        String result = processor.getLastNumericColumnName(numericColumns);
        assertEquals(" TestColumn3 ", result);
    }

    /**
     * Tests for the extractUniqueValuesFromLastNumericColumn method.
     *
     * Test scenarios:
     * 1. Normal case: Verifies extraction of unique values after filtering.
     * 2. Filters exclude all rows: Ensures the result is empty when no data passes the filters.
     * 3. Empty numericColumns list: Ensures IllegalArgumentException is thrown.
     * 4. Empty data list: Verifies the result is empty when data is empty.
     * 5. Empty filters: Verifies the method works without filters.
     * 6. Invalid inputs: Ensures IllegalArgumentException is thrown for null parameters.
     * 7. Filter error: Ensures RuntimeException is thrown when an error occurs during filtering.
     */

    @Tag("Non-isolated unit tests")
    @DisplayName("Verify unique values are extracted from the last numeric column after filtering")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_NormalCase() {
        List<Map<String, String>> data = List.of(
                Map.of("TestColumn1", "10", "TestColumn2", "A"),
                Map.of("TestColumn1", "20", "TestColumn2", "B"),
                Map.of("TestColumn1", "10", "TestColumn2", "C")
        );
        List<String> numericColumns = List.of("TestColumn1");
        Map<String, List<String>> columnFilters = Map.of("TestColumn2", List.of("A", "B"));

        List<String> result = processor.extractUniqueValuesFromLastNumericColumn(data, numericColumns, columnFilters);
        assertEquals(List.of("10", "20"), result);
    }

    @Tag("Non-isolated unit tests")
    @DisplayName("Verify empty result when filters exclude all rows")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_FiltersExcludeAll() {
        List<Map<String, String>> data = List.of(
                Map.of("TestColumn1", "10", "TestColumn2", "A"),
                Map.of("TestColumn1", "20", "TestColumn2", "B")
        );
        List<String> numericColumns = List.of("TestColumn1");
        Map<String, List<String>> columnFilters = Map.of("TestColumn2", List.of("C"));

        List<String> result = processor.extractUniqueValuesFromLastNumericColumn(data, numericColumns, columnFilters);
        assertTrue(result.isEmpty());
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when numeric columns list is empty")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_EmptyNumericColumns() {
        List<Map<String, String>> data = List.of(
                Map.of("TestColumn1", "10", "TestColumn2", "A")
        );
        Map<String, List<String>> columnFilters = Map.of("TestColumn2", List.of("A"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            processor.extractUniqueValuesFromLastNumericColumn(data, Collections.emptyList(), columnFilters);
        });
        assertEquals("Numeric columns cannot be null or empty.", exception.getMessage());
    }

    @Tag("Non-isolated unit tests")
    @DisplayName("Verify empty result is returned when input data is empty after filtering")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_EmptyData() {
        List<String> numericColumns = List.of("TestColumn1");
        Map<String, List<String>> columnFilters = Map.of("TestColumn2", List.of("A"));

        List<String> result = processor.extractUniqueValuesFromLastNumericColumn(Collections.emptyList(), numericColumns, columnFilters);
        assertTrue(result.isEmpty());
    }

    @Tag("Non-isolated unit tests")
    @DisplayName("Verify unique values are extracted when filters are empty")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_EmptyFilters() {
        List<Map<String, String>> data = List.of(
                Map.of("TestColumn1", "10", "TestColumn2", "A"),
                Map.of("TestColumn1", "20", "TestColumn2", "B")
        );
        List<String> numericColumns = List.of("TestColumn1");

        List<String> result = processor.extractUniqueValuesFromLastNumericColumn(data, numericColumns, Collections.emptyMap());
        assertEquals(List.of("10", "20"), result);
    }

    @Tag("unit")
    @DisplayName("Verify exception is thrown when input parameters are null")
    @Test
    void testExtractUniqueValuesFromLastNumericColumn_NullParameters() {
        List<Map<String, String>> data = List.of(
                Map.of("TestColumn1", "10", "TestColumn2", "A")
        );
        List<String> numericColumns = List.of("TestColumn1");
        Map<String, List<String>> columnFilters = Map.of("TestColumn2", List.of("A"));

        assertThrows(IllegalArgumentException.class, () -> processor.extractUniqueValuesFromLastNumericColumn(null, numericColumns, columnFilters));
        assertThrows(IllegalArgumentException.class, () -> processor.extractUniqueValuesFromLastNumericColumn(data, null, columnFilters));
        assertThrows(IllegalArgumentException.class, () -> processor.extractUniqueValuesFromLastNumericColumn(data, numericColumns, null));
    }

}
