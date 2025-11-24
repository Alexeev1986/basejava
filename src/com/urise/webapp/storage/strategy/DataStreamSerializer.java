package com.urise.webapp.storage.strategy;

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

            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());

            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();

                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> items = ((ListSection) section).getItems();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Organization> orgSections =((OrganizationsSection) section).getOrganizations();
                        dos.writeInt(orgSections.size());
                        for (Organization organization : orgSections) {
                            List<Position> positions = organization.getPositions();
                            Organization.Link link = organization.getLink();

                            dos.writeInt(positions.size());
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            for (Position position: positions) {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate() != null ? position.getEndDate().toString() : "");
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {

        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            int size = dis.readInt();

            for (int i = 0; i < size; i++){
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String value = dis.readUTF();
                resume.setContact(contactType, value);
            }

            size = dis.readInt();

            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL, OBJECTIVE -> {
                        String content = dis.readUTF();
                        resume.setSection(sectionType, new TextSection(content));
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        int itemsCount = dis.readInt();
                        List<String> items = new ArrayList<>(itemsCount);
                        for (int j = 0; j < itemsCount; j++) {
                            items.add(dis.readUTF());
                        }
                        resume.setSection(sectionType, new ListSection(items));
                    }
                    case EXPERIENCE, EDUCATION -> {
                        int orgCount = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(orgCount);
                        for (int j = 0; j < orgCount; j++) {
                            int positionsCount = dis.readInt();
                            String linkName = dis.readUTF();
                            String linkUrl = dis.readUTF();
                            Organization.Link link = new Organization.Link(linkName, linkUrl);

                            List<Position> positions = new ArrayList<>(positionsCount);
                            for (int k = 0; k < positionsCount; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                String endDateStr = dis.readUTF();
                                LocalDate endDate = endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr);
                                String title = dis.readUTF();
                                String discription = dis.readUTF();
                                positions.add(new Position(startDate, endDate, title, discription));
                            }
                            organizations.add(new Organization(link, positions));
                        }
                        resume.setSection(sectionType, new OrganizationsSection(organizations));
                    }
                }

            }

            return resume;
        }
    }
}
