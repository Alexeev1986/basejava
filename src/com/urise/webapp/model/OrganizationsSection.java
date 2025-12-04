package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationsSection extends AbstractSection implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Organization> organizations;


    public OrganizationsSection() {
        this.organizations = new ArrayList<>();
    }

    public OrganizationsSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = List.copyOf(organizations);
    }

    public void add(String nameOrg, String urlOrg, List<Position> positions) {
        organizations.add(new Organization(nameOrg, urlOrg, positions));
    }

    public void add(String nameOrg, String urlOrg, Position position) {
        organizations.add(new Organization(nameOrg, urlOrg, position));
    }

    public List<Organization> getOrganizations() {
        return List.copyOf(organizations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization entry : organizations) {
            sb.append(entry.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrganizationsSection that = (OrganizationsSection) object;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(organizations);
    }
}