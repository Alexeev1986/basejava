package com.urise.webapp.model;


import java.util.Objects;

public class Period extends OrganizationsSection {
    private final String startDate;
    private final String endDate;
    private final String title;
    private final String description;

    public Period(String startDate, String endDate, String title, String description) {
        this.startDate = Objects.requireNonNull(startDate, "start date must not be null");
        this.endDate = endDate;
        this.title = Objects.requireNonNull(title, "organization must not be null");
        this.description = description != null ? description : "";
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return startDate + " - " +
                (endDate != null ? endDate : "настоящее время") + " : " +
                title + "\n" +
                description.indent(22) + "\n";
        }
}