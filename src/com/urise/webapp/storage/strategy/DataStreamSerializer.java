package com.urise.webapp.storage.strategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

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

    private void writeSections(DataOutputStream dos, Map<SectionType, AbstractSection> sections) throws IOException {
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

    private void writeSectionContent(DataOutputStream dos,
                                     SectionType type, AbstractSection section) throws IOException {
        switch (type) {
            case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
            case ACHIEVEMENT, QUALIFICATIONS -> writeListSection(dos, (ListSection) section);
            case EXPERIENCE, EDUCATION -> writeOrganizationsSection(dos, (OrganizationsSection) section);
            default -> throw new StorageException("Error unresolved section.");
        }
    }

    private void writeListSection(DataOutputStream dos, ListSection section) throws IOException {
        writeCollection(dos, section.getItems(), (stream, item) -> {
            try {
                stream.writeUTF(item);
            } catch (IOException e) {
                throw new StorageException("Write list section exception", e);
            }
        });
    }

    private void writeOrganizationsSection(DataOutputStream dos,
                                           OrganizationsSection section) throws IOException {
        writeCollection(dos, section.getOrganizations(), (stream, organization) -> {
            try {
                writeOrganization(stream, organization);
            } catch (IOException e) {
                throw new StorageException("Write organization section error", e);
            }
        });
    }

    private void writeOrganization(DataOutputStream dos, Organization organization) throws IOException {
        List<Position> positions = organization.getPositions();
        Organization.Link link = organization.getLink();
        dos.writeUTF(link.getName());
        dos.writeUTF(link.getUrl());
        writeCollection(dos, organization.getPositions(), this::writePositions);
    }

    private void writePositions(DataOutputStream dos, Position position) {
        try {
            dos.writeUTF(position.getStartDate().toString());
            dos.writeUTF(position.getEndDate() != null ? position.getEndDate().toString() : "");
            dos.writeUTF(position.getTitle());
            dos.writeUTF(position.getDescription());
        } catch (Exception e) {
            throw new StorageException("Write position error", e);
        }
    }

    private <T> void writeCollection(DataOutputStream dos, List<T> items, BiConsumer<DataOutputStream, T> biCons) throws IOException {
        dos.writeInt(items.size());
        for (T item : items) {
            biCons.accept(dos, item);
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
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
            AbstractSection section = readSectionContent(dis, sectionType);
            resume.setSection(sectionType, section);
        }
    }

    private AbstractSection readSectionContent(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> readListSection(dis);
            case EXPERIENCE, EDUCATION -> readOrganizationsSection(dis);
        };
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        List<String> items = readCollection(dis, stream -> {
            try {
                return dis.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ListSection(items);
    }

    private OrganizationsSection readOrganizationsSection(DataInputStream dis) throws IOException {
        List<Organization> organizations = readCollection(dis, stream -> {
            try {
                return readOrganization(dis);
            } catch (IOException e) {
                throw new StorageException("Read organization error", e);
            }
        });
        return new OrganizationsSection(organizations);
    }

    private Organization readOrganization(DataInputStream dis) throws IOException {
        String linkName = dis.readUTF();
        String linkUrl = dis.readUTF();
        Organization.Link link = new Organization.Link(linkName, linkUrl);
        List<Position> positions = readCollection(dis, (stream) -> {
            try {
                return readPositions(stream);
            } catch (IOException e) {
                throw new StorageException("Read position error", e);
            }
        });

        return new Organization(link, positions);
    }

    private Position readPositions(DataInputStream dis) throws IOException {
        LocalDate startDate = LocalDate.parse(dis.readUTF());
        String endDateStr = dis.readUTF();
        LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr);
        String title = dis.readUTF();
        String description = dis.readUTF();
        return new Position(startDate, endDate, title, description);
    }

    private <T> List<T> readCollection(DataInputStream dis,
                                       Function<DataInputStream, T> function) throws IOException {
        int size = dis.readInt();
        List<T> items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            items.add(function.apply(dis));
        }
        return items;
    }
}
