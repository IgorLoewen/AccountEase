package com.accountease.amazonseller.core.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the DateFilter class.
 *
 * These tests ensure the correctness of the date filtering logic provided by the DateFilter methods,
 * specifically the `filter` method. They validate that the rows are filtered accurately based on
 * the date range provided in the class constructor, and that the filtering behavior is robust and predictable.
 *
 * The primary focus of these tests is to verify that the `filter` method includes only those rows
 * whose dates fall within the specified range and excludes rows with invalid or missing data. This ensures
 * the integrity of the filtering logic, which is critical for report generation and other business logic
 * that depends on accurate data filtering.
 *
 * These tests cover the following scenarios:
 * - Valid rows with dates inside the range are correctly included in the result.
 * - Rows with dates outside the range are excluded from the result.
 * - Rows with invalid date formats are ignored, and no exceptions are thrown during filtering.
 * - Rows that lack the required date column are safely ignored.
 *
 * Additionally, the tests validate the constructor of the DateFilter class to ensure that it handles
 * invalid input data (e.g., incorrect date formats) by throwing the expected exceptions. This ensures
 * the correctness of object initialization and prevents the creation of improperly configured objects.
 *
 * These tests are essential for maintaining the reliability and stability of the DateFilter class. Any changes
 * to the filtering logic, constructor behavior, or date handling rules will cause the tests to fail,
 * helping to quickly identify and resolve potential issues. They serve as a safeguard to ensure
 * predictable behavior and correct implementation of the date filtering functionality.
 */

@Epic("Date Filtering")
@DisplayName("DateFiltersTest")
public class DateFilterTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final String DATE_COLUMN_EXCEL = "Datum/Uhrzeit";
    private static final String START_DATE = "01.01.2025 00:00:00";
    private static final String END_DATE = "10.01.2025 23:59:59";

    private DateFilter dateFilter;

    @BeforeEach
    public void setUp() throws ParseException {
        dateFilter = new DateFilter(DATE_COLUMN_EXCEL, START_DATE, END_DATE, DATE_FORMAT);
    }

    @Test
   @DisplayName("Filter rows with valid dates")
    public void testFilterValidData() {
        // Arrange
        List<Map<String, String>> data = List.of(
                Map.of(DATE_COLUMN_EXCEL, "05.01.2025 12:30:00", "value", "valid") // Valid date
        );

        // Act
        List<Map<String, String>> result = dateFilter.filter(data);

        // Assert
        assertEquals(1, result.size(), "Expected exactly one row to pass the filter.");
    }

    @Test
    @DisplayName("Filter rows with out-of-range dates")
    public void testFilterOutOfRangeData() {
        // Arrange
        List<Map<String, String>> data = List.of(
                Map.of(DATE_COLUMN_EXCEL, "15.01.2025 08:45:00", "value", "outOfRange") // Out of range
        );

        // Act
        List<Map<String, String>> result = dateFilter.filter(data);

        // Assert
        assertEquals(0, result.size(), "Expected no rows to pass the filter.");
    }

    @Test
    @DisplayName("Filter rows with invalid date format")
    public void testFilterInvalidDateFormat() {
        // Arrange
        List<Map<String, String>> data = List.of(
                Map.of(DATE_COLUMN_EXCEL, "DateFilterTestDate", "value", "invalid") // Invalid format
        );

        // Act
        List<Map<String, String>> result = dateFilter.filter(data);

        // Assert
        assertEquals(0, result.size(), "Expected no rows to pass the filter with invalid dates.");
    }

    @Test
    @DisplayName("Filter rows with missing date column")
    public void testFilterMissingDateColumn() {
        // Arrange
        List<Map<String, String>> data = List.of(
                Map.of("value", "missingDate") // Missing date column
        );

        // Act
        List<Map<String, String>> result = dateFilter.filter(data);

        // Assert
        assertEquals(0, result.size(), "Expected no rows to pass the filter without date column.");
    }

    @Test
   @DisplayName("Constructor with invalid date strings")
    public void testConstructorInvalidDates() {
        // Act & Assert
        assertThrows(ParseException.class, () ->
                        new DateFilter(DATE_COLUMN_EXCEL, "invalid-start", END_DATE, DATE_FORMAT),
                "Expected constructor to throw ParseException for invalid start date."
        );
        assertThrows(ParseException.class, () ->
                        new DateFilter(DATE_COLUMN_EXCEL, START_DATE, "invalid-end", DATE_FORMAT),
                "Expected constructor to throw ParseException for invalid end date."
        );
    }
}