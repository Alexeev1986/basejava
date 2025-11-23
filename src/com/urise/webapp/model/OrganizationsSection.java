package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationsSection extends Section implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Organization> entries = new ArrayList<>();

    public OrganizationsSection() {
    }

    public void add(String nameOrg, String urlOrg, List<Position> positions) {
        entries.add(new Organization(nameOrg, urlOrg, positions));
    }

    public void add(String nameOrg, String urlOrg, Position position) {
        entries.add(new Organization(nameOrg, urlOrg, position));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization entry : entries) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrganizationsSection that = (OrganizationsSection) object;
        return Objects.equals(entries, that.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(entries);
    }
}