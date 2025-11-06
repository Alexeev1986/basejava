package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationsSection extends Section {
    private final List<OrganizationEntry> entries = new ArrayList<>();

    public void add(String nameOrg, String urlOrg, List<Period> periods) {
        entries.add(new OrganizationEntry(nameOrg, urlOrg, periods));
    }

    public void add(String nameOrg, String urlOrg, Period period) {
        entries.add(new OrganizationEntry(nameOrg, urlOrg, period));
    }

    public List<OrganizationEntry> getAll() {
        return List.copyOf(entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (OrganizationEntry entry : entries) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString().trim();

    }
}