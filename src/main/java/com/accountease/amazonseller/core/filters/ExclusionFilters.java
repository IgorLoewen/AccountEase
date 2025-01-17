package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;

import java.util.Map;

/**
 * The ExclusionFilters class provides methods for creating {@link ReportSetting} objects
 * that define filters with exclusion conditions.
 *
 * These filters play a key role in the logic of report generation. They ensure that data
 * is excluded or included in the report strictly in accordance with the specified filtering rules.
 *
 * The importance of this class lies in defining the base parameters for data filtering.
 * Any changes to the filter logic (e.g., modifications to parameters, data structures, or business rules)
 * directly impact the accuracy and integrity of the generated reports.
 *
 * All constants used, such as {@link FilterConstants}, must be configured correctly and
 * meet the program's expectations. This class is closely integrated with the business logic
 * and is critically important for maintaining the system's stability and reliable performance.
 */

public class ExclusionFilters {

    public static ReportSetting getTotalServiceFees() {
        return new ReportSetting(
                "Servicegebühren",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_SERVICEGEBUEHR,
                        FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.EXCLUDE_WERBEKOSTEN
                ),
                FilterConstants.NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalFBALogisticsInventoryCredits() {
        return new ReportSetting(
                "FBA Lagerbestandsguthaben",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ANPASSUNG,
                        FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.EXCLUDE_ALLGEMEINE_ANPASSUNG
                ),
                FilterConstants.NUMERIC_GESAMT
        );
    }
}
