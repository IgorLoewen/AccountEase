package com.accountease.amazonseller.core.filters;

import com.accountease.amazonseller.core.ReportSetting;
import com.accountease.amazonseller.core.constants.FilterConstants;

import java.util.Map;

/**
 * The StandardFilters class provides methods for creating {@link ReportSetting} objects
 * that define filters with fixed standard conditions.
 *
 * These filters play a key role in the logic of report generation, ensuring that data
 * is processed strictly according to the specified parameters. They are used to define
 * the key aspects of report generation, data filtering, and business operations.
 *
 * The importance of this class lies in defining standard filter parameters
 * that directly affect the accuracy and integrity of the generated reports. Any changes
 * to the filter logic (e.g., parameter modifications, data structure updates, or business rules)
 * can disrupt the stability of the system.
 *
 * Constants such as {@link FilterConstants} must be correctly configured and fully
 * aligned with the program's expectations. This class is closely integrated with the application's
 * business logic and is critically important for ensuring the system's stable operation.
 */
public class StandardFilters {

    public static ReportSetting getTotalSellerShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Verkäufer",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                        FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_VERKAEUFER
                ),
                FilterConstants.NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalAmazonShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Amazon",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                        FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_AMAZON
                ),
                FilterConstants.NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalSalesSumSeller() {
        return new ReportSetting(
                "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                        FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_VERKAEUFER
                ),
                FilterConstants.NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalSalesSumAmazon() {
        return new ReportSetting(
                "Verkäufe mit Versand durch Amazon",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG,
                        FilterConstants.COLUMN_VERSAND, FilterConstants.VERSAND_AMAZON
                ),
                FilterConstants.NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalAdvertisingCosts() {
        return new ReportSetting(
                "Werbekosten",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_SERVICEGEBUEHR,
                        FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.BESCHREIBUNG_WERBEKOSTEN
                ),
                FilterConstants.NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalAmazonFulfillmentFees() {
        return new ReportSetting(
                "Transaktionsgebühren Versand durch Amazon",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                ),
                FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalPromotionalDiscountsFees() {
        return new ReportSetting(
                "Werbeaktionsrabatte",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                ),
                FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalAdjustmentsFees() {
        return new ReportSetting(
                "Anpassungen",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ANPASSUNG,
                        FilterConstants.COLUMN_BESCHREIBUNG, FilterConstants.BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG
                ),
                FilterConstants.NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalStorageAndServiceFeesForAmazonFulfillment() {
        return new ReportSetting(
                "Lagerbestands- und Service-Gebühren für Versand durch Amazon",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_LAGERGEBUEHREN
                ),
                FilterConstants.NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalRefundsForShippingCredits() {
        return new ReportSetting(
                "Erstattungen für Versandkostengutschriften",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                ),
                FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }

    public static ReportSetting getTotalRefundsForPromotionalDiscounts() {
        return new ReportSetting(
                "Erstattungen zur Werbeaktionsrabatt",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                ),
                FilterConstants.NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalShippingCreditNotes() {
        return new ReportSetting(
                "Versandkostengutschriften",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_BESTELLUNG
                ),
                FilterConstants.NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }


    public static ReportSetting getTotalRefundsForAmazonShippedItems() {
        return new ReportSetting(
                "Erstattungen für durch Amazon versandte Artikel",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                ),
                FilterConstants.NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalRefundsForAmazonTransactionFees() {
        return new ReportSetting(
                "Ersattungen zur Transaktionsgebühr - Versand durch Amazon",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG
                ),
                FilterConstants.NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalRefundAmountForReturnedShipments() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        FilterConstants.COLUMN_TYP, FilterConstants.TYP_ERSTATTUNG

                ),
                FilterConstants.NUMERIC_VERKAUFSGEBUEHREN
        );
    }



}
