package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationsSection extends Section {
    private final List<Organization> entries = new ArrayList<>();

    public void add(String nameOrg, String urlOrg, List<Position> positions) {
        entries.add(new Organization(nameOrg, urlOrg, positions));
    }

    public void add(String nameOrg, String urlOrg, Position position) {
        entries.add(new Organization(nameOrg, urlOrg, position));
    }

    public List<Organization> getAll() {
        return List.copyOf(entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization entry : entries) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString().trim();

    }
}