package com.urise.webapp.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthAdapter extends XmlAdapter<String, YearMonth> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    @Override
    public YearMonth unmarshal(String value) {
        return YearMonth.parse(value, FORMATTER);
    }

    @Override
    public String marshal(YearMonth value) {
        return value.format(FORMATTER);
    }
}