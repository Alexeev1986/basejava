package com.urise.webapp.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthAdapter extends XmlAdapter<String, YearMonth> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    @Override
    public YearMonth unmarshal(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return YearMonth.parse(value, FORMATTER);
    }

    @Override
    public String marshal(YearMonth value) {
        if (value == null) {
            return null;
        }
        return value.format(FORMATTER);
    }
}