package com.accountease.amazonseller.core;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ReportSetting class.
 *
 * This class validates the basic functionality of the ReportSetting class:
 * - Constructor initialization of fields
 * - Correct implementation of the `toString` method
 *
 * The tests ensure the core properties of the ReportSetting class work as expected.
 */
@Tag("unit")
@Epic("Report Setting Functionality")
@DisplayName("ReportSettingTest")
class ReportSettingTest {

    private ReportSetting reportSetting;

    /**
     * Sets up the test environment before each test.
     *
     * Initializes a ReportSetting instance with predefined test data.
     */
    @BeforeEach
    void setUp() {
        Map<String, List<String>> columnFilters = Map.of("TestColumn", List.of("Value1", "Value2"));
        List<String> numericColumns = List.of("NumericColumn");

        reportSetting = new ReportSetting("Test Report", columnFilters, numericColumns);
    }

    /**
     * Validates the constructor of the ReportSetting class.
     *
     * This test ensures that the fields of the ReportSetting instance are initialized correctly.
     */
    @Test
    @DisplayName("Verify constructor initializes fields correctly")
    void testConstructor() {
        assertEquals("Test Report", reportSetting.getName());
        assertEquals(Map.of("TestColumn", List.of("Value1", "Value2")), reportSetting.getColumnFilters());
        assertEquals(List.of("NumericColumn"), reportSetting.getNumericColumns());
    }

    /**
     * Validates the `toString` method of the ReportSetting class.
     *
     * This test ensures that the `toString` method returns the expected string representation of the object.
     */
    @Test
    @DisplayName("Verify toString method returns correct string")
    void testToString() {
        String expected = "ReportSetting{name='Test Report', columnFilters={TestColumn=[Value1, Value2]}, numericColumns=[NumericColumn]}";
        assertEquals(expected, reportSetting.toString());
    }
}
