package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.storage.strategy.DataStreamSerializer;
import java.io.File;

public class MainTestFile {
    private static final File DIRECTORY = new File(".\\storage");
    private static final Storage STORAGE = new FileStorage(DIRECTORY, new DataStreamSerializer());

    public static void main(String[] args) {
        STORAGE.clear();

        STORAGE.save(new Resume("uuid1", "Alexeev Anton"));

        STORAGE.save(ResumeTestData.createResume("uuid3", "Kraeva Svetlana"));

        Resume resume = STORAGE.get("uuid3");

        ResumeTestData.printResume(resume);
    }
}
