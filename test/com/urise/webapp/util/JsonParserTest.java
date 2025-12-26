package com.urise.webapp.util;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.TextSection;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume2 = JsonParser.read(json, Resume.class);
        assertEquals(RESUME_1, resume2);
    }

    @Test
    void write() {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        assertEquals(section1, section2);
    }
}