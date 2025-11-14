package com.urise.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
