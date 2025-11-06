package com.urise.webapp.model;

import java.util.Collections;
import java.util.List;

public class OrganizationEntry {
    private final String name;
    private final String url;
    private final List<Period> periods;

    public OrganizationEntry(String name, String url, List<Period> periods) {
        this.name = name;
        this.url = url;
        this.periods = List.copyOf(periods);
    }

    public OrganizationEntry(String name, String url, Period period) {
        this.name = name;
        this.url = url;
        this.periods = Collections.singletonList(period);

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "-".repeat(25);

        sb.append(prefix).append(name);
        if (url != null && !url.isEmpty()) {
            sb.append(" [").append(url).append("]");
        }
        sb.append("\n");
        sb.append(periods.toString());
        return sb.toString();
    }
}
