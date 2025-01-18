package com.accountease.amazonseller.core.reader;

import com.accountease.amazonseller.core.constants.FilterConstants;
import org.junit.jupiter.api.*;
import io.qameta.allure.Epic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ExcelReader class.
 *
 * These tests verify the core functionality of reading data from an Excel file
 * and converting it into a list of maps where column headers serve as keys.
 *
 * Key Points:
 * - These are unit tests, focused on the logic of the `readExcel` method.
 * - Although static final variables from the FilterConstants class are used,
 *   they represent dynamic data relevant to the tested method. This allows
 *   the tests to remain isolated and ensures consistency with potential integration tests.
 *
 * Test Scenarios:
 *
 * 1. **Read valid Excel file (`testReadValidExcelFile`):**
 *    Ensures that data is successfully read from a valid Excel file.
 *
 * 2. **Verify rows after headers are read correctly (`testNoRowsAfterHeaders`):**
 *    Confirms that rows following the header row are processed correctly
 *    and contain valid data.
 *
 * 3. **Ensure header row contains exactly 27 columns (`testHeaderRowColumnCount`):**
 *    Validates that the header row matches the expected structure.
 *    If this test fails, it indicates a change in the file format that requires manual intervention.
 *
 * Importance:
 * - These tests serve as a baseline to ensure the stability of the `readExcel` method.
 * - They flag potential issues with changes to the file structure or data format early,
 *   allowing for quick adjustments in the code.
 */
@Tag("unit")
@Epic("Excel File Reader and Data Mapping")
@DisplayName("ExcelReaderTest")
class ExcelReaderTest {

    private static final String TEST_FILE = FilterConstants.FILE_PATH;
    public static final int HEADER_ROW_INDEX = FilterConstants.HEADER_ROW_INDEX;


    @Test
    @DisplayName("Read valid Excel file")
    void testReadValidExcelFile() throws IOException {
        List<Map<String, String>> result = ExcelReader.readExcel(TEST_FILE, FilterConstants.HEADER_ROW_INDEX);

        assertNotNull(result, "Result should not be null.");
        assertFalse(result.isEmpty(), "Result should contain rows of data.");
    }


    @Test
    @DisplayName("Ensure header row contains exactly 27 column names")
    void testHeaderRowColumnCount() throws IOException {

        List<Map<String, String>> result = ExcelReader.readExcel(TEST_FILE, FilterConstants.HEADER_ROW_INDEX);

        assertFalse(result.isEmpty(), "Data rows should not be empty.");
        Map<String, String> headerRow = result.get(0);
        assertEquals(27, headerRow.size(),
                "Header row should contain exactly 27 columns. "
                        + "If this test fails, it indicates that the Excel file structure has changed. "
                        + "Please manually check the file's structure and update the code accordingly."
        );
    }

    @Test
    @DisplayName("Throw IllegalArgumentException if header row is null")
    void testHeaderRowIsNull() {
        int invalidHeaderRowIndex = 1000000000;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExcelReader.readExcel(TEST_FILE, invalidHeaderRowIndex);
        });

        assertTrue(exception.getMessage().contains("Header row not found at index"),
                "Expected exception message indicating missing header row.");
    }

    @Test
    @DisplayName("Handle mixed cell types in header row")
    void testMixedCellTypesInHeaderRow() throws IOException {

        List<Map<String, String>> result = ExcelReader.readExcel(TEST_FILE, HEADER_ROW_INDEX);

        assertNotNull(result, "Result should not be null.");
        assertFalse(result.isEmpty(), "Result should contain rows of data.");
    }

}