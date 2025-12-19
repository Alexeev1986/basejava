package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume extends AbstractSection implements Comparable<Resume>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
    private String uuid;
    private String fullName;

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getContacts(ContactType type) {
        return contacts.get(type);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContact(ContactType type, String value) {
        if (value == null || value.isBlank()) {
            contacts.remove(type);
        } else {
            contacts.put(type, value.trim());
        }
    }

    public void setSection(SectionType type, AbstractSection section) {
        if (section == null) {
            sections.remove(type);
        } else {
            sections.put(type, section);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public AbstractSection getSections(SectionType type) {
        return sections.get(type);
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections) &&
                Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contacts, sections, uuid, fullName);
    }

    @Override
    public String toString() {
        return uuid + " | " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        return result == 0 ? uuid.compareTo(o.uuid) : result;
    }
}
