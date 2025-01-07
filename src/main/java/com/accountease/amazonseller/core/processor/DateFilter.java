package com.accountease.amazonseller.core.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is responsible for filtering rows based on a date range.
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
