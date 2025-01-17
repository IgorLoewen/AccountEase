package com.accountease.amazonseller.core.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The DateFilter class provides methods for filtering rows of data based on a specified date range.
 *
 * This class plays a crucial role in ensuring accurate date-based filtering of data, which is critical
 * for report generation and business logic that relies on precise data selection.
 *
 * The importance of this class lies in its ability to include only those rows that fall within the specified
 * date range, while excluding rows with invalid or missing data. This ensures the integrity of the filtering logic
 * and prevents errors in data processing.
 *
 * Key functionalities of the class include:
 * - Filtering rows whose dates fall within the specified range.
 * - Ignoring rows with invalid date formats or missing values without throwing exceptions.
 * - Handling the valid date range passed to the constructor, with proper validation.
 *
 * Any changes to the filtering logic or date handling directly impact the system's stability.
 * This class must be configured correctly to ensure predictable behavior and the accurate
 * execution of the application's business logic.
 */
public class DateFilter {
    private final String dateColumn;
    private final Date startDate;
    private final Date endDate;
    private final SimpleDateFormat dateFormat;

    public DateFilter(String dateColumn, String startDateStr, String endDateStr, SimpleDateFormat dateFormat) throws ParseException {
        this.dateColumn = dateColumn;
        this.dateFormat = dateFormat;
        this.startDate = dateFormat.parse(startDateStr);
        this.endDate = dateFormat.parse(endDateStr);
    }

    /**
     * Filters the provided data based on the date range.
     *
     * @param data List of rows represented as maps of column names to values.
     * @return Filtered list of rows.
     */
    public List<Map<String, String>> filter(List<Map<String, String>> data) {
        List<Map<String, String>> filteredData = new ArrayList<>();

        for (Map<String, String> row : data) {
            String dateStr = row.getOrDefault(dateColumn, "");
            if (dateStr.isEmpty()) continue;

            try {
                Date rowDate = dateFormat.parse(dateStr.replace(" UTC", ""));
                if (!rowDate.before(startDate) && !rowDate.after(endDate)) {
                    filteredData.add(row);
                }
            } catch (ParseException e) {
                System.err.println("Invalid date format for row: " + dateStr);
            }
        }

        return filteredData;
    }
}
