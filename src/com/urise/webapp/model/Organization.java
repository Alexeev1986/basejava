package com.urise.webapp.model;

import java.util.Collections;
import java.util.List;

public class Organization {
    private final String name;
    private final String url;
    private final List<Position> positions;

    public Organization(String name, String url, List<Position> positions) {
        this.name = name;
        this.url = url;
        this.positions = List.copyOf(positions);
    }

    public Organization(String name, String url, Position position) {
        this.name = name;
        this.url = url;
        this.positions = Collections.singletonList(position);

    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Position> getPeriods() {
        return positions;
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
        sb.append(positions.toString());
        return sb.toString();
    }
}
