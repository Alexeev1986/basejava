package com.urise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    MAIL("Email") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    LINKEDIN("LinkedIn") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    GITHUB("GitHub") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    STACKOVERFLOW("Stackoverflow") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    },
    HOMEPAGE("Домашняя страница") {
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }
    };

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String toLink(String href) {
        return "<a href=" + getTitle() + ":" + href + ">" + href + "</a>";
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
