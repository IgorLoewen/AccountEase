package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.constants.FilterConstants;
import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.util.*;
import java.io.IOException;

/**
 * Class ReportSetting
 *
 * This class represents the configuration and processing logic for generating reports based on Excel data.
 * It provides mechanisms to filter data by columns, dates, and calculate totals for numeric columns.
 *
 * Constructor ReportSetting:
 * 1. Initializes a new instance of the `ReportSetting` class with the specified parameters:
 *    - `name`: A unique identifier for the report.
 *    - `columnFilters`: A map where keys represent column names and values are lists of allowed values for filtering.
 *    - `numericColumns`: A list of numeric column names for summation.
 * 2. The data is statically initialized by reading from an Excel file via the `ExcelReader` class.
 *
 * Method initData:
 * - Reads data from the Excel file specified by `FilterConstants.FILE_PATH` and `FilterConstants.HEADER_ROW_INDEX`.
 * - Converts the data into a list of maps, where each map represents a row, with keys as column headers.
 * - Throws a `RuntimeException` if there is an error reading the file.
 *
 * Method processReport:
 * - Executes the report processing logic in the following steps:
 *   1. Filters the data by date using `DateFilter`.
 *   2. Applies multi-column filters using `MultiColumnFilter`.
 *   3. Calculates the total sum of numeric columns using `SummationProcessor`.
 * - Returns the calculated total as a `Double`.
 * - Throws a `RuntimeException` if an error occurs during processing.
 *
 * Key Features:
 * - Static initialization of Excel data ensures consistent data availability across instances.
 * - Flexible filtering using customizable date and column filters.
 * - Efficient summation of numeric data for quick report generation.
 *
 * Parameters:
 * param name           The name of the report configuration.
 * param columnFilters  A map specifying filters for specific columns.
 * param numericColumns A list of numeric column names for summation.
 *
 * Returns:
 * - The `processReport` method returns the total sum of the specified numeric columns after filtering.
 *
 * Example Usage:
 * Given an Excel file with the following content:
 * ```
 * Date       Product   Quantity  Price
 * 2025-01-01 A         10        100
 * 2025-01-02 B         20        200
 * ```
 * Creating a `ReportSetting` instance:
 * ```
 * Map<String, List<String>> filters = Map.of("Product", List.of("A"));
 * List<String> numericColumns = List.of("Price");
 * ReportSetting report = new ReportSetting("ExampleReport", filters, numericColumns);
 * ```
 * Calling `processReport` would:
 * - Filter the data to include only rows where "Product" is "A".
 * - Calculate the total for the "Price" column: `100`.
 *
 * Significance:
 * - This class is essential for automating the generation of customizable reports from structured data.
 * - It encapsulates the logic for filtering and summation, ensuring modularity and reusability.
 */


public class ReportSetting {
    private final String name;
    private final Map<String, List<String>> columnFilters;
    private final List<String> numericColumns;

    private static final List<Map<String, String>> data = initData();

    public ReportSetting(String name, Map<String,List<String>> columnFilters, List<String> numericColumns) {
        this.name = name;
        this.columnFilters = columnFilters;
        this.numericColumns = numericColumns;
    }

    private static List<Map<String, String>> initData() {
        try {
            return ExcelReader.readExcel(FilterConstants.FILE_PATH, FilterConstants.HEADER_ROW_INDEX);
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    public String getName() {
        return name;
    }
    public Map<String, List<String>> getColumnFilters() {
        return columnFilters;
    }
    public List<String> getNumericColumns() {
        return numericColumns;
    }
    public static List<Map<String, String>> getData() {
        return data;
    }



    public Double processReport() {
        try {

            DateFilter dateFilter = new DateFilter(
                    FilterConstants.DATE_COLUMN_EXCEL,
                    FilterConstants.START_DATE,
                    FilterConstants.END_DATE,
                    FilterConstants.DATE_FORMAT
            );
            List<Map<String, String>> dateFilteredData = dateFilter.filter(data);

            MultiColumnFilter filter = new MultiColumnFilter();
            List<Map<String, String>> filteredData = filter.filterByColumns(columnFilters, dateFilteredData);

            SummationProcessor processor = new SummationProcessor();
            return processor.calculateTotalSum(filteredData, numericColumns);
        } catch (Exception e) {
            throw new RuntimeException("Error processing the report: " + e.getMessage(), e);
        }
    }


    @Override
    public String toString() {
        return "ReportSetting{name='" + name + '\'' +
                ", columnFilters=" + columnFilters +
                ", numericColumns=" + numericColumns +
                '}';
    }



}
