package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section implements Serializable {
    private final List<String> items;

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ListSection that = (ListSection) object;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }
}
