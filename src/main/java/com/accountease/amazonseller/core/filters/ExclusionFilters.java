package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;

import java.util.Map;

/**
 * Класс для фильтров с исключающими условиями.
 *
 * Эти фильтры используются для фильтрации данных, исключая определённые значения из выборки.
 * Все остальные данные, которые не попадают под исключения, включаются в отчёт.
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
