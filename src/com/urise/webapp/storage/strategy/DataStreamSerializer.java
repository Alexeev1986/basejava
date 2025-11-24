package com.urise.webapp.storage.strategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeContacts(dos, r.getContacts());
            writeSections(dos, r.getSections());
        }
    }

    private void writeContacts(DataOutputStream dos, Map<ContactType, String> contacts) throws IOException {
        dos.writeInt(contacts.size());
        contacts.forEach((type, value) -> {
            try {
                dos.writeUTF(type.name());
                dos.writeUTF(value);
            } catch (IOException e) {
                throw new StorageException("Write contacts error", e);
            }
        });
    }

    private void writeSections(DataOutputStream dos, Map<SectionType, Section> sections) throws IOException {
        dos.writeInt(sections.size());
        sections.forEach((type, section) -> {
            try {
                dos.writeUTF(type.name());
                writeSectionContent(dos, type, section);
            } catch (Exception e) {
                throw new StorageException("Write sections error", e);
            }
        });
    }

    private void writeSectionContent(DataOutputStream dos, SectionType type, Section section) throws IOException {
        switch (type) {
            case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
            case ACHIEVEMENT, QUALIFICATIONS -> writeListSection(dos, (ListSection) section);
            case EXPERIENCE, EDUCATION -> writeOrganizationsSection(dos, (OrganizationsSection) section);
        }
    }

    private void writeListSection(DataOutputStream dos, ListSection section) throws IOException {
        List<String> items = section.getItems();
        dos.writeInt(items.size());
        items.forEach((item) -> {
            try {
                dos.writeUTF(item);
            } catch (IOException e) {
                throw new StorageException("Write list section error", e);
            }
        });
    }

    private void writeOrganizationsSection(DataOutputStream dos, OrganizationsSection section) throws IOException {
        List<Organization> orgSections = section.getOrganizations();
        dos.writeInt(orgSections.size());
        orgSections.forEach(organization -> {
            try {
                writeOrganization(dos, organization);
            } catch (IOException e) {
                throw new StorageException("Write organization section error", e);
            }
        });
    }

    private void writeOrganization(DataOutputStream dos, Organization organization) throws IOException {
        List<Position> positions = organization.getPositions();
        Organization.Link link = organization.getLink();

        dos.writeInt(positions.size());
        dos.writeUTF(link.getName());
        dos.writeUTF(link.getUrl());

        positions.forEach(position -> {
            try {
                dos.writeUTF(position.getStartDate().toString());
                dos.writeUTF(position.getEndDate() != null ? position.getEndDate().toString() : "");
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());
            } catch (Exception e) {
                throw new StorageException("Write position error", e);
            }
        });
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readContacts(dis, resume);
            readSections(dis, resume);
            return resume;
        }
    }

    private void readContacts(DataInputStream dis, Resume resume) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            ContactType contactType = ContactType.valueOf(dis.readUTF());
            String value = dis.readUTF();
            resume.setContact(contactType, value);
        }
    }

    private void readSections(DataInputStream dis, Resume resume) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            Section section = readSectionContent(dis, sectionType);
            resume.setSection(sectionType, section);
        }
    }

    private Section readSectionContent(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> readListSection(dis);
            case EXPERIENCE, EDUCATION -> readOrganizationsSection(dis);
        };
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int itemsCount = dis.readInt();
        List<String> items = new ArrayList<>(itemsCount);
        for (int j = 0; j < itemsCount; j++) {
            items.add(dis.readUTF());
        }
        return new ListSection(items);
    }

    private OrganizationsSection readOrganizationsSection(DataInputStream dis) throws IOException {
        int orgCount = dis.readInt();
        List<Organization> organizations = new ArrayList<>(orgCount);
        for (int j = 0; j < orgCount; j++) {
            organizations.add(readOrganization(dis));
        }
        return new OrganizationsSection(organizations);
    }

    private Organization readOrganization(DataInputStream dis) throws IOException {
        int posCount = dis.readInt();
        String linkName = dis.readUTF();
        String linkUrl = dis.readUTF();
        Organization.Link link = new Organization.Link(linkName, linkUrl);
        List<Position> positions = new ArrayList<>(posCount);
        for (int k = 0; k < posCount; k++) {
            LocalDate startDate = LocalDate.parse(dis.readUTF());
            String endDateStr = dis.readUTF();
            LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr);
            String title = dis.readUTF();
            String description = dis.readUTF();
            positions.add(new Position(startDate, endDate, title, description));
        }
        return new Organization(link, positions);
    }

}
