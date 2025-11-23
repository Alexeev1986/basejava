package com.urise.webapp.model;


import com.urise.webapp.util.YearMonthAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serial;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth startDate;

    @XmlJavaTypeAdapter(YearMonthAdapter.class)
    private YearMonth endDate;

    private String title;
    private String description;

    public Position() {}

    public Position(YearMonth startDate, YearMonth endDate, String title, String description) {
        this.startDate = Objects.requireNonNull(startDate, "start date must not be null");
        this.endDate = endDate;
        this.title = Objects.requireNonNull(title, "organization must not be null");
        this.description = description != null ? description : "";
    }

    @Override
    public String toString() {
        return startDate + " - " + (endDate != null ? endDate : "настоящее время") + " : " + title + "\n" + description.indent(22) + "\n";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return Objects.equals(startDate, position.startDate) &&
                Objects.equals(endDate, position.endDate) &&
                Objects.equals(title, position.title) &&
                Objects.equals(description, position.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDate, endDate, title, description);
    }
}