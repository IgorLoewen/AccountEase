package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.utils.FilterConstants;

import java.util.List;
import java.util.Map;

/**
 * Класс для фильтров, возвращающих полный список значений из указанных колонок.
 *
 * Эти фильтры применяются для получения всех значений из определённой колонки без их модификации.
 * Полный список значений может быть использован для дальнейшей фильтрации, анализа или отображения.
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

    public static ReportSetting createFilteredReportFromAnother(ReportSetting firstReport, ReportSetting templateReport) {
        return new ReportSetting(
                templateReport.getName(),
                Map.of(templateReport.getColumnFilters().keySet().iterator().next(),
                        firstReport.getColumnFilters().getOrDefault(
                                firstReport.getColumnFilters().keySet().iterator().next(), List.of()
                        )
                ),
                templateReport.getNumericColumns()
        );
    }
}
