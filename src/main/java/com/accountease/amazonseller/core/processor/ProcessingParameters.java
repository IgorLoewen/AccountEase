package com.accountease.amazonseller.core.processor;

import java.text.SimpleDateFormat;

public class ProcessingParameters {
    private String filePath;
    private String startDate;
    private String endDate;
    private SimpleDateFormat dateFormat;

    public ProcessingParameters(String filePath, String startDate, String endDate, String dateFormatPattern) {
        this.filePath = filePath;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateFormat = new SimpleDateFormat(dateFormatPattern);
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
