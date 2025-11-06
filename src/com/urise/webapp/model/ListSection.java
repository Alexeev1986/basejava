package com.urise.webapp.model;

import java.util.List;

public class ListSection extends Section {
    private final List<String> items;

    public ListSection(List<String> items) {
        this.items = items;
    }

    public List<String> getAll() {
        return List.copyOf(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : items) {
            sb.append(" - ").append(str).append("\n");
        }
        return sb.toString();
    }
}
