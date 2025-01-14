package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;

import java.util.List;
import java.util.Map;

public class ReportFilterSettings {

    // Названия колонок
    private static final String COLUMN_TYP = "Typ";
    private static final String COLUMN_VERSAND = "Versand";
    private static final String COLUMN_BESCHREIBUNG = "Beschreibung";
    private static final String COLUMN_BESTELLNUMMER = "Bestellnummer";

    // Уникальные значения колонок
    private static final List<String> TYP_BESTELLUNG = List.of("Bestellung");
    private static final List<String> TYP_SERVICEGEBUEHR = List.of("Servicegebühr");
    private static final List<String> TYP_ANPASSUNG = List.of("Anpassung");
    private static final List<String> TYP_LAGERGEBUEHR = List.of("Versand durch Amazon Lagergebühr");
    private static final List<String> TYP_ERSTATTUNG = List.of("Erstattung");

    private static final List<String> VERSAND_VERKAEUFER = List.of("Verkäufer");
    private static final List<String> VERSAND_AMAZON = List.of("Amazon");

    private static final List<String> BESCHREIBUNG_WERBEKOSTEN = List.of("Werbekosten");
    private static final List<String> BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG = List.of("Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung");

    // ❗ Особые случаи: исключающие фильтры
    private static final List<String> EXCLUDE_WERBEKOSTEN = List.of("!exclude", "Werbekosten");
    private static final List<String> EXCLUDE_ALLGEMEINE_ANPASSUNG = List.of("!exclude", "Versand durch Amazon Erstattung für Lagerbestand -  Allgemeine Anpassung", "Versand durch Amazon Erstattung für Lagerbestand - Kundenrücksendung");

    // Названия cуммарных итогов для вывода
    private static final List<String> NUMERIC_VERKAUFSGEBUEHREN = List.of("Verkaufsgebühren");
    private static final List<String> NUMERIC_UMSAETZE = List.of("Umsätze");
    private static final List<String> NUMERIC_GESAMT = List.of("Gesamt");
    private static final List<String> NUMERIC_GEBUEHREN_VERSAND_AMAZON = List.of("Gebühren zu Versand durch Amazon");
    private static final List<String> NUMERIC_RABATTE_AUS_WERBEAKTIONEN = List.of("Rabatte aus Werbeaktionen");
    private static final List<String> NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN = List.of("Gutschrift für Versandkosten");

    // Параметры для вывода уникальных значений из колонки
    private static final List<String> UNIQUE_BESTELLNUMMER = List.of("Bestellnummer");

    private static final List<String> UNIQUE_LIST_BESTELLNUMMER = List.of("Bestellnummer");

    /**
     * Фильтры для стандартной обработки данных.
     *
     * Эти методы создают объекты ReportSetting, предназначенные для выполнения стандартной
     * фильтрации данных с учётом заранее заданных параметров. Такие фильтры применяются для
     * подсчёта итогов, анализа и построения отчётов на основе различных категорий данных.
     *
     * Основная задача:
     * - Фильтрация данных по указанным критериям. Например:
     *   1. Фильтрация заказов по типу доставки (продажи, отправленные продавцом или Amazon).
     *   2. Анализ расходов (затраты на рекламу, комиссии за обработку).
     *   3. Учёт возвратов и компенсаций.
     *
     * Применение:
     * - Эти фильтры используются для создания отчётов с заданной логикой обработки данных.
     * - Каждый фильтр настраивается на определённую категорию данных (например, продажи, расходы, возвраты).
     *
     * Пример использования:
     * - Создание отчёта о продажах, выполненных продавцом:
     *   ReportSetting sellerShippingFee = ReportFilterSettings.getTotalSellerShippingFee();
     *
     * - Анализ возвратов за отправку:
     *   ReportSetting refundsForShippingCredits = ReportFilterSettings.getTotalRefundsForShippingCredits();
     *
     * Особенности:
     * - Каждый метод задаёт:
     *   1. Название отчёта (например, "Verkaufsgebühren Versand durch Verkäufer").
     *   2. Фильтры, которые применяются к данным (`columnFilters`).
     *   3. Числовые колонки (`numericColumns`), используемые для подсчёта итогов.
     * - Эти фильтры универсальны и охватывают большинство задач по обработке данных.
     */
    public static ReportSetting getTotalSellerShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Verkäufer",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_VERKAEUFER
                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalAmazonShippingFee() {
        return new ReportSetting(
                "Verkaufsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_AMAZON
                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }

    public static ReportSetting getTotalSalesSumSeller() {
        return new ReportSetting(
                "Verkäufe, die durch Verkäufer selbst verschickt wurden",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_VERKAEUFER
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalSalesSumAmazon() {
        return new ReportSetting(
                "Verkäufe mit Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG,
                        COLUMN_VERSAND, VERSAND_AMAZON
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalAdvertisingCosts() {
        return new ReportSetting(
                "Werbekosten",
                Map.of(
                        COLUMN_TYP, TYP_SERVICEGEBUEHR,
                        COLUMN_BESCHREIBUNG, BESCHREIBUNG_WERBEKOSTEN
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalAmazonFulfillmentFees() {
        return new ReportSetting(
                "Transaktionsgebühren Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalPromotionalDiscountsFees() {
        return new ReportSetting(
                "Werbeaktionsrabatte",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalAdjustmentsFees() {
        return new ReportSetting(
                "Anpassungen",
                Map.of(
                        COLUMN_TYP, TYP_ANPASSUNG,
                        COLUMN_BESCHREIBUNG, BESCHREIBUNG_FBA_ERSTATTUNG_LAGERBESTAND_ANPASSUNG
                ),
                NUMERIC_GESAMT
        );
    }



    public static ReportSetting getTotalStorageAndServiceFeesForAmazonFulfillment() {
        return new ReportSetting(
                "Lagerbestands- und Service-Gebühren für Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_LAGERGEBUEHR
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalRefundsForShippingCredits() {
        return new ReportSetting(
                "Erstattungen für Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }

    public static ReportSetting getTotalRefundsForPromotionalDiscounts() {
        return new ReportSetting(
                "Erstattungen zur Werbeaktionsrabatt",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_RABATTE_AUS_WERBEAKTIONEN
        );
    }

    public static ReportSetting getTotalShippingCreditNotes() {
        return new ReportSetting(
                "Versandkostengutschriften",
                Map.of(
                        COLUMN_TYP, TYP_BESTELLUNG
                ),
                NUMERIC_GUTSCHRIFT_FUER_VERSANDKOSTEN
        );
    }


    public static ReportSetting getTotalRefundsForAmazonShippedItems() {
        return new ReportSetting(
                "Erstattungen für durch Amazon versandte Artikel",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_UMSAETZE
        );
    }

    public static ReportSetting getTotalRefundsForAmazonTransactionFees() {
        return new ReportSetting(
                "Ersattungen zur Transaktionsgebühr - Versand durch Amazon",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG
                ),
                NUMERIC_GEBUEHREN_VERSAND_AMAZON
        );
    }

    public static ReportSetting getTotalRefundAmountForReturnedShipments() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG

                ),
                NUMERIC_VERKAUFSGEBUEHREN
        );
    }


    /**
     * Фильтры с исключающими условиями.
     *
     * Эти методы создают объекты ReportSetting, которые фильтруют данные, исключая
     * определённые значения из выборки. Все остальные данные, которые не попадают под
     * исключения, включаются в отчёт.
     *
     * Основная задача:
     * - Фильтрация данных с учётом исключений. Например:
     *   1. Исключение определённых типов затрат (например, "Werbekosten" в Servicegebühren).
     *   2. Исключение конкретных описаний (например, общих корректировок в FBA Lagerbestandsguthaben).
     *
     * Применение:
     * - Эти фильтры полезны для случаев, когда требуется работать с данными, за исключением
     *   определённых значений, которые не должны включаться в отчёт.
     * - Применяются для специфических задач, например, при анализе затрат или возвратов,
     *   где важно исключить незначительные или нерелевантные записи.
     *
     * Пример использования:
     * - Создание отчёта, исключающего рекламные расходы:
     *   ReportSetting serviceFeesReport = ReportFilterSettings.getTotalServiceFees();
     *
     * - Создание отчёта, исключающего общие корректировки:
     *   ReportSetting inventoryCreditsReport = ReportFilterSettings.getTotalFBALogisticsInventoryCredits();
     *
     * Особенности:
     * - Исключающие фильтры задаются в параметрах Map, где ключ — это колонка,
     *   а значение — список исключений.
     * - Такие фильтры позволяют гибко настраивать отчёты под специфические требования.
     */

    public static ReportSetting getTotalServiceFees() {
        return new ReportSetting(
                "Servicegebühren",
                Map.of(
                        COLUMN_TYP, TYP_SERVICEGEBUEHR,
                        COLUMN_BESCHREIBUNG, EXCLUDE_WERBEKOSTEN  // Исключающий фильтр
                ),
                NUMERIC_GESAMT
        );
    }

    public static ReportSetting getTotalFBALogisticsInventoryCredits() {
        return new ReportSetting(
                "FBA Lagerbestandsguthaben",
                Map.of(
                        COLUMN_TYP, TYP_ANPASSUNG,
                        COLUMN_BESCHREIBUNG, EXCLUDE_ALLGEMEINE_ANPASSUNG  // Исключающий фильтр
                ),
                NUMERIC_GESAMT
        );
    }




    /**
     * Фильтры для получения полного списка значений из указанных колонок.
     *
     * Эти методы создают объекты ReportSetting, предназначенные для фильтрации данных
     * и извлечения полного списка значений из определённой колонки.
     *
     * Основная задача:
     * - Применение фильтров для получения всех значений из указанной колонки без их модификации.
     * - Использование полного списка значений может быть полезно для:
     *   1. Дальнейшей фильтрации (например, передача значений в другой отчёт или использование их в бизнес-логике).
     *   2. Прямого анализа данных (например, для отчётов, статистики или отображения сырых данных).
     *
     * Применение:
     * - Эти фильтры удобны для извлечения таких данных, как номера заказов, транзакции, типы операций и т.д.
     * - Полный список значений используется как источник данных для других шагов обработки.
     *
     * Пример использования:
     * - Получение списка значений из определённой колонки:
     *   ReportSetting fullColumnFilter = ReportFilterSettings.getUniqueValuesFromFilteredColumn();
     *
     * Особенности:
     * - Методы возвращают настроенный ReportSetting, где указана колонка, из которой извлекается полный список значений.
     * - Каждый метод настроен на работу с конкретными типами данных.
     */

    public static ReportSetting getUniqueValuesFromFilteredColumn() {
        return new ReportSetting(
                "Reine Rückerstattungskosten zur Verkaufsgebühr, ohne berechneten Rückerstattungsgebühren",
                Map.of(
                        COLUMN_TYP, TYP_ERSTATTUNG

                ),

                UNIQUE_LIST_BESTELLNUMMER
        );
    }

    /**
     * Создаёт объект ReportSetting на основе уникальных значений, извлечённых из определённой колонки.
     *
     * Этот метод используется для создания фильтра, в который передаётся список уникальных значений.
     * Эти значения применяются к указанной колонке (COLUMN_BESTELLNUMMER) и используются
     * для фильтрации данных.
     *
     * Применение:
     * - Метод получает список уникальных значений (uniqueValues) как параметр.
     * - Эти значения автоматически устанавливаются в фильтр указанной колонки.
     * - Результат — готовый объект ReportSetting с применёнными фильтрами.
     *
     * Использование:
     * - Этот метод удобен, если у вас уже есть список уникальных значений, извлечённых из данных.
     * - Подходит для случаев, когда нужно использовать заранее подготовленный набор данных
     *   для дальнейшей обработки.
     *
     * @param uniqueValues Список уникальных значений, которые применяются к фильтру.
     * @return Новый объект ReportSetting с установленными фильтрами.
     */
    public static ReportSetting buildFilterFromUniqueColumnValues(List<String> uniqueValues) {
        return new ReportSetting(
                "Bearbeitungsgebühren für Erstattungen", // Название отчёта
                Map.of(
                        COLUMN_BESTELLNUMMER, uniqueValues // Применяем уникальные значения к колонке
                ),
                NUMERIC_VERKAUFSGEBUEHREN // Указываем числовые колонки для подсчёта
        );
    }

    /**
     * Создаёт новый объект ReportSetting, используя уникальные значения, извлечённые из первого отчёта.
     *
     * Этот метод выполняет следующие шаги:
     * 1. Извлекает уникальные значения из последней числовой колонки первого отчёта (firstReport),
     *    применяя фильтры, заданные в этом отчёте.
     * 2. Применяет эти уникальные значения к шаблону второго отчёта (templateReport) для создания
     *    нового фильтра.
     *
     * Использование:
     * - Первый отчёт (firstReport) используется для выборки уникальных значений.
     * - Второй отчёт (templateReport) служит шаблоном, в который встраиваются уникальные значения.
     * - Результат — новый объект ReportSetting, готовый к дальнейшей обработке.
     *
     * @param firstReport Первый отчёт, на основе которого извлекаются уникальные значения.
     * @param templateReport Шаблон второго отчёта, куда добавляются уникальные значения.
     * @return Новый объект ReportSetting с обновлёнными фильтрами.
     */
    public static ReportSetting createFilteredReportFromAnother(ReportSetting firstReport, ReportSetting templateReport) {
        // Извлекаем уникальные значения из первого объекта
        UniqueValuesProcessor processor = new UniqueValuesProcessor();
        List<String> uniqueValues = processor.extractUniqueValuesFromLastNumericColumn(
                ReportSetting.getData(),
                firstReport.getNumericColumns(),
                firstReport.getColumnFilters()
        );

        // Возвращаем новый объект, модифицируя второй шаблон
        return new ReportSetting(
                templateReport.getName(), // Имя берём из второго объекта (шаблона)
                Map.of(templateReport.getColumnFilters().keySet().iterator().next(), uniqueValues), // Применяем уникальные значения
                templateReport.getNumericColumns()
        );
    }




}
