package com.urise.webapp.model;

import java.util.Collections;
import java.util.List;

public class Organization {
    private final Link link;
    private final List<Position> positions;

    public Organization(String name, String url, List<Position> positions) {
        this.link = new Link(name, url);
        this.positions = List.copyOf(positions);
    }

    public Organization(String name, String url, Position position) {
        this.link = new Link(name, url);
        this.positions = Collections.singletonList(position);

    }

    public Link getLink() {
        return link;
    }
    public List<Position> getPeriods() {
        return positions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "-".repeat(25);

        sb.append(prefix).append(link).append("\n");
        sb.append(positions.toString());
        return sb.toString();
    }

    public static class Link {
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
    }
}
