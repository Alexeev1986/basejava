package com.urise.webapp.model;


import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Position extends OrganizationsSection {
    private final YearMonth startDate;
    private YearMonth endDate;
    private final String title;
    private final String description;

    public Position(String startDate, String endDate, String title, String description) {
        this.startDate = Objects.requireNonNull(YearMonth.parse(startDate, DateTimeFormatter.ofPattern("MM/yyyy")),
                "start date must not be null");
        if (endDate != null && !endDate.isEmpty()) {
            this.endDate = YearMonth.parse(endDate, DateTimeFormatter.ofPattern("MM/yyyy"));
        }

        this.title = Objects.requireNonNull(title, "organization must not be null");
        this.description = description != null ? description : "";
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public YearMonth getEndDate() {
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