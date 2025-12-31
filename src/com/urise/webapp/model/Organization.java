package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", Position.EMPTY);

    private Link link;

    private List<Position> positions;

    public Organization() {
    }

    public Organization(Link link, List<Position> positions) {
        Objects.requireNonNull(link.getName(), "name must not be null");
        this.link = new Link(link.getName(), link.getUrl());
        this.positions = List.copyOf(positions);
    }

    public Organization(Link link, Position position) {
        Objects.requireNonNull(link.getName(), "name must not be null");
        this.link = new Link(link.getName(), link.getUrl());
        this.positions = Collections.singletonList(position);
    }

    public Organization(String name, String url, List<Position> positions) {
        Objects.requireNonNull(name, "name must not be null");
        this.link = new Link(name, url);
        this.positions = List.copyOf(positions);
    }

    public Organization(String name, String url, Position position) {
        Objects.requireNonNull(name, "name must not be null");
        this.link = new Link(name, url);
        this.positions = Collections.singletonList(position);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Link getLink() {
        return link;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "-".repeat(25);
        sb.append(prefix).append(link).append("\n");
        sb.append(positions.toString());
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Organization that = (Organization) object;
        return Objects.equals(link, that.link) && Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Link implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;
        private String url;

        public Link() {
        }

        public Link(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            if (url != null && !url.isBlank()) {
                return name + ": " + url;
            }
            return name;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Link link = (Link) object;
            return Objects.equals(name, link.name) && Objects.equals(url, link.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, url);
        }
    }
}
