package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Link link;
    private final List<Position> positions;

    public Organization(String name, String url, List<Position> positions) {
        Objects.requireNonNull(name, "neme must not be null");
        this.link = new Link(name, url);
        this.positions = List.copyOf(positions);
    }

    public Organization(String name, String url, Position position) {
        Objects.requireNonNull(name, "neme must not be null");
        this.link = new Link(name, url);
        this.positions = Collections.singletonList(position);

    }

    public Link getLink() {
        return link;
    }
    public List<Position> getOrganizations() {
        return List.copyOf(positions);
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

    public static class Link implements Serializable{
        @Serial
        private static final long serialVersionUID = 1L;

        private final String name;
        private final String url;

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
