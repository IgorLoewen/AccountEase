package com.accountease.amazonseller.core.reader;

import com.accountease.amazonseller.core.constants.FilterConstants;
import org.junit.jupiter.api.*;
import io.qameta.allure.Epic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Excel Reader")
@DisplayName("Tests for ExcelReader Class")
class ExcelReaderTest {

    private static final String TEST_FILE = FilterConstants.FILE_PATH;

    @Test
    @DisplayName("Read valid Excel file")
    void testReadValidExcelFile() throws IOException {
        List<Map<String, String>> result = ExcelReader.readExcel(TEST_FILE, FilterConstants.HEADER_ROW_INDEX);

        assertNotNull(result, "Result should not be null.");
        assertFalse(result.isEmpty(), "Result should contain rows of data.");
    }

    @Test
    @DisplayName("Throw IOException for invalid file format")
    void testInvalidFileFormat() {
        String invalidFile = "src/test/resources/invalid_file.txt";

        assertThrows(IOException.class, () -> {
            ExcelReader.readExcel(invalidFile, FilterConstants.HEADER_ROW_INDEX);
        }, "Expected IOException for invalid file format.");
    }







}
