package com.accountease.amazonseller.core.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import io.qameta.allure.Epic;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class SummationProcessorTest
 *
 * This class contains unit tests for the SummationProcessor class, which is responsible for calculating
 * the total sum of values from specified numeric columns. The tests cover all critical scenarios,
 * including correct summation, handling of invalid data, empty strings, and various edge cases.
 *
 * Key aspects tested:
 * 1. **Correct summation of values from multiple columns**:
 *    - Ensures the method correctly sums values from the specified columns.
 *    - Example: If the columns contain numbers, the result should be their sum.
 *
 * 2. **Handling of invalid values**:
 *    - Verifies that invalid values (e.g., strings instead of numbers) are ignored.
 *    - Example: The value `"invalid"` should not affect the result.
 *
 * 3. **Handling of empty strings**:
 *    - Ensures that empty values in rows are ignored and do not affect the result.
 *
 * 4. **Handling of empty datasets**:
 *    - Verifies that the method returns `0.0` when the dataset is empty.
 *
 * 5. **Handling of empty column lists**:
 *    - Ensures that the method returns `0.0` when the list of columns is empty.
 *
 * 6. **Handling of `null` data and columns**:
 *    - Verifies that the method handles cases where the dataset or column list is `null`, returning `0.0`.
 *
 * 7. **Mixed edge cases**:
 *    - Tests the method's behavior when the dataset and columns contain a mix of invalid values,
 *      empty strings, and valid numbers.
 *
 * Significance of the tests:
 * - These tests ensure the stability and correctness of the `calculateTotalSum` method,
 *   which is a critical part of the business logic.
 * - Covering all edge cases helps prevent potential bugs and guarantees that the method works
 *   correctly with any input data.
 */

@Epic("Summation Processor")
@DisplayName("SummationProcessorTest")
public class SummationProcessorTest {

    private SummationProcessor summationProcessor;
    private List<Map<String, String>> testData;

    @BeforeEach
    public void setUp() {
        summationProcessor = new SummationProcessor();
        testData = List.of(
                Map.of("testColumnA", "10", "testColumnB", "20.5", "testColumnC", "30"),
                Map.of("testColumnA", "5", "testColumnB", "15.5", "testColumnC", ""),
                Map.of("testColumnA", "invalid", "testColumnB", "25", "testColumnC", "35"),
                Map.of("testColumnA", "7.5", "testColumnB", "", "testColumnC", "42.5")
        );
    }

    @Test
    @DisplayName("Calculate total sum for multiple numeric columns")
    public void testCalculateTotalSumMultipleColumns() {
        List<String> numericColumns = List.of("testColumnA", "testColumnB");

        Double result = summationProcessor.calculateTotalSum(testData, numericColumns);

        assertEquals(83.5, result, 0.001, "Expected total sum for columns A and B to be 83.5.");
    }


    @Test
    @DisplayName("Handle invalid numeric values in columns")
    public void testCalculateTotalSumWithInvalidValues() {
        List<String> numericColumns = List.of("testColumnA");

        Double result = summationProcessor.calculateTotalSum(testData, numericColumns);

        assertEquals(22.5, result, 0.001, "Expected total sum for column A (ignoring invalid values) to be 22.5.");
    }

    @Test
    @DisplayName("Handle empty rows and columns")
    public void testCalculateTotalSumWithEmptyValues() {
        List<String> numericColumns = List.of("testColumnB", "testColumnC");

        Double result = summationProcessor.calculateTotalSum(testData, numericColumns);

        assertEquals(168.5, result, 0.001, "Expected total sum for columns B and C to be 138.0.");
    }

    @Test
    @DisplayName("Handle empty data set")
    public void testCalculateTotalSumWithEmptyData() {
        List<Map<String, String>> emptyData = List.of();
        List<String> numericColumns = List.of("testColumnA", "testColumnB", "testColumnC");

        Double result = summationProcessor.calculateTotalSum(emptyData, numericColumns);

        assertEquals(0.0, result, "Expected total sum for empty data set to be 0.0.");
    }

    @Test
    @DisplayName("Handle empty numeric columns")
    public void testCalculateTotalSumWithEmptyColumns() {
        List<String> numericColumns = List.of();

        Double result = summationProcessor.calculateTotalSum(testData, numericColumns);

        assertEquals(0.0, result, "Expected total sum for empty numeric columns to be 0.0.");
    }

    @Test
    @DisplayName("Handle null data")
    public void testCalculateTotalSumWithNullData() {
        List<String> numericColumns = List.of("testColumnA", "testColumnB", "testColumnC");

        Double result = summationProcessor.calculateTotalSum(null, numericColumns);

        assertEquals(0.0, result, "Expected total sum for null data to be 0.0.");
    }

    @Test
    @DisplayName("Handle null numeric columns")
    public void testCalculateTotalSumWithNullColumns() {
        Double result = summationProcessor.calculateTotalSum(testData, null);

        assertEquals(0.0, result, "Expected total sum for null numeric columns to be 0.0.");
    }

    @Test
    @DisplayName("Handle mixed edge cases in data and columns")
    public void testCalculateTotalSumWithMixedCases() {
        List<Map<String, String>> mixedData = List.of(
                Map.of("testColumnA", "10", "testColumnB", "", "testColumnC", "invalid"),
                Map.of("testColumnA", "5", "testColumnB", "invalid", "testColumnC", "20"),
                Map.of("testColumnA", "", "testColumnB", "15.5", "testColumnC", "")
        );

        List<String> numericColumns = List.of("testColumnA", "testColumnB", "testColumnC");

        Double result = summationProcessor.calculateTotalSum(mixedData, numericColumns);

        assertEquals(50.5, result, 0.001, "Expected total sum for mixed cases to be 50.5.");
    }

}
