package com.accountease.amazonseller.core;

import com.accountease.amazonseller.core.processor.DateFilter;
import com.accountease.amazonseller.core.processor.MultiColumnFilter;
import com.accountease.amazonseller.core.processor.SummationProcessor;
import com.accountease.amazonseller.core.processor.UniqueValuesProcessor;
import com.accountease.amazonseller.core.reader.ExcelReader;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class ReportSetting {
    private final String name; // Название отчёта
    private final Map<String, List<String>> columnFilters; // Фильтры по колонкам
    private final List<String> numericColumns; // Колонки для подсчёта

    // Глобальные параметры
    private static final String filePath = "/Users/GiorUg/Desktop/Desktop PC bis 2023/2024CompleteReportTransaktions.xlsx";
    private static final int headerRowIndex = 7; // Строка заголовков
    private static final String startDate = "01.07.2024 00:00:00";
    private static final String endDate = "31.12.2024 23:59:59";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static final String DateColumnExel = "Datum/Uhrzeit";

    // Считываем данные из Excel (статическое поле)
    private static final List<Map<String, String>> data = initData();

    public ReportSetting(String name, Map<String, List<String>> columnFilters, List<String> numericColumns) {
        this.name = name;
        this.columnFilters = columnFilters;
        this.numericColumns = numericColumns;
    }

    // Метод инициализации data
    private static List<Map<String, String>> initData() {
        try {
            return ExcelReader.readExcel(filePath, headerRowIndex);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении Excel-файла: " + e.getMessage(), e);
        }
    }

    public String getName() {
        return name;
    }

    public Double processReport() {
        try {
            // Фильтрация данных
            DateFilter dateFilter = new DateFilter(DateColumnExel, startDate, endDate, dateFormat);
            List<Map<String, String>> dateFilteredData = dateFilter.filter(data);

            MultiColumnFilter filter = new MultiColumnFilter();
            List<Map<String, String>> filteredData = filter.filterByColumns(columnFilters, dateFilteredData);

            // Подсчёт суммы
            SummationProcessor processor = new SummationProcessor();
            return processor.calculateTotalSum(filteredData, numericColumns);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка обработки отчёта: " + e.getMessage(), e);
        }
    }


    /**
     * Возвращает последнюю колонку из списка numericColumns.
     *
     * Используется для автоматического определения колонки, по которой нужно извлекать уникальные значения.
     * Список numericColumns задается при создании объекта ReportSetting.
     *
     * @return Последняя колонка из numericColumns.
     * @throws IllegalStateException Если список numericColumns пуст или не инициализирован.
     */
    public String getLastNumericColumn() {
        if (numericColumns == null || numericColumns.isEmpty()) {
            throw new IllegalStateException("Список numericColumns пуст.");
        }
        // Возвращаем последнюю колонку
        return numericColumns.get(numericColumns.size() - 1);
    }

    /**
     * Извлекает уникальные значения из последней колонки, указанной в numericColumns.
     *
     * 1. Сначала метод определяет последнюю колонку из списка numericColumns с помощью getLastNumericColumn.
     * 2. Затем применяет фильтры, указанные в columnFilters, к данным (data).
     * 3. После фильтрации извлекает уникальные значения из выбранной колонки.
     *
     * Пример использования:
     * List<String> uniqueValues = reportSetting.extractUniqueValuesFromLastNumericColumn();
     *
     * @return Список уникальных значений из последней колонки numericColumns.
     * @throws RuntimeException Если произошла ошибка во время фильтрации данных или извлечения уникальных значений.
     */
    public List<String> extractUniqueValuesFromLastNumericColumn() {
        try {
            // Шаг 1: Получаем последнюю колонку из numericColumns
            String lastNumericColumn = getLastNumericColumn();

            // Шаг 2: Применяем фильтры из columnFilters к данным
            MultiColumnFilter filter = new MultiColumnFilter();
            List<Map<String, String>> filteredData = filter.filterByColumns(columnFilters, data);

            // Шаг 3: Извлекаем уникальные значения из последней numericColumns
            UniqueValuesProcessor uniqueValuesProcessor = new UniqueValuesProcessor();
            return uniqueValuesProcessor.getUniqueValues(filteredData, lastNumericColumn);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при извлечении уникальных значений: " + e.getMessage(), e);
        }
    }




}
