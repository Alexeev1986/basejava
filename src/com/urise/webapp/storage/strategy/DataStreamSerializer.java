package com.urise.webapp.storage.strategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.Organization;
import com.urise.webapp.model.OrganizationsSection;
import com.urise.webapp.model.Position;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
        writeCollection(dos, contacts.entrySet(), entry -> {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        });
    }

    private void writeSections(DataOutputStream dos,
                               Map<SectionType, AbstractSection> sections) throws IOException {
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
        writeCollection(dos, section.getItems(), dos::writeUTF);
    }

    private void writeOrganizationsSection(DataOutputStream dos,
                                           OrganizationsSection section) throws IOException {
        writeCollection(dos, section.getOrganizations(), (organization) ->
                writeOrganization(dos, organization));
    }

    private void writeOrganization(DataOutputStream dos, Organization organization) throws IOException {
        Organization.Link link = organization.getLink();
        dos.writeUTF(link.getName());
        dos.writeUTF(link.getUrl());
        writeCollection(dos, organization.getPositions(), (position) -> writePositions(dos, position));
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

    interface Executed<T> {
        void accept(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> items,
                                     Executed<T> executed) throws IOException {
        dos.writeInt(items.size());
        for (T item : items) {
            executed.accept(item);
        }
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

    private AbstractSection readSectionContent(DataInputStream dis,
                                               SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> readListSection(dis);
            case EXPERIENCE, EDUCATION -> readOrganizationsSection(dis);
        };
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        return new ListSection(readCollection(dis, dis::readUTF));
    }

    private OrganizationsSection readOrganizationsSection(DataInputStream dis) throws IOException {
        return new OrganizationsSection(readCollection(dis, () -> readOrganization(dis)));
    }

    private Organization readOrganization(DataInputStream dis) throws IOException {
        return new Organization(new Organization.Link(dis.readUTF(),
                dis.readUTF()), readCollection(dis, () -> readPositions(dis)));
    }

    private Position readPositions(DataInputStream dis) throws IOException {
        LocalDate startDate = LocalDate.parse(dis.readUTF());
        String endDateStr = dis.readUTF();
        LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr);
        String title = dis.readUTF();
        String description = dis.readUTF();
        return new Position(startDate, endDate, title, description);
    }

    private interface ReadElement<T> {
        T readElements() throws IOException;
    }

    private <T> List<T> readCollection(DataInputStream dis,
                                       ReadElement<T> readElement) throws IOException {
        int size = dis.readInt();
        List<T> items = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            items.add(readElement.readElements());
        }
        return items;
    }
}
