package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;

import java.util.List;
import java.util.Map;

/**
 * The ValueListFilters class provides methods for creating {@link ReportSetting} objects
 * that define filters based on lists of unique values.
 *
 * These filters are used for dynamically configuring data filtering conditions. They play an important role
 * in providing flexible and precise configurations for report generation and data processing.
 *
 * The importance of this class lies in ensuring accurate data filtering based on unique values,
 * which is critical to the program's functionality. Any changes to the filter logic, such as modifications
 * to parameters or data structures, can lead to system failures.
 *
 * The methods in this class allow extracting unique values from specified columns and using them
 * to create dynamic filters. Constants such as {@link FilterConstants} must be correctly configured
 * to ensure the application's business logic is executed accurately.
 */
public class ValueListFilters {



    public static ReportSetting getUniqueValuesFromFilteredColumn() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                ),
                FilterConstants.UNIQUE_LIST_BESTELLNUMMER
        );
    }

    public static ReportSetting buildFilterFromUniqueColumnValues(List<String> uniqueValues) {
        return new ReportSetting(
                "Bearbeitungsgebühren für Erstattungen",
                Map.of(
                        FilterConstants.COLUMN_BESTELLNUMMER, uniqueValues
                ),
                FilterConstants.NUMERIC_VERKAUFSGEBUEHREN
        );
    }

}
