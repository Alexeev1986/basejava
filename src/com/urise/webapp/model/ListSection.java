package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> items;

    public static final ListSection EMPTY = new ListSection(List.of(""));
    public ListSection() {
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = List.copyOf(items);
    }

    public List<String> getItems() {
        return List.copyOf(items);
    }

    @Override
    public String toString() {
        return String.join("\n", items);
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
