package com.urise.webapp.model;


import com.urise.webapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    private String title;
    private String description;

    public Position() {
    }

    public Position(int startYear, Month startMonth, String title, String description) {
        this(of(startYear, startMonth), NOW, title, description);
    }

    public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        //Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
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