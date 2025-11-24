package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.storage.strategy.DataStreamSerializer;

import java.io.File;

public class MainTestFile {
    private static final File directory = new File(".\\storage");
    private static final Storage storage = new FileStorage(directory, new DataStreamSerializer());

    public static void main(String[] args) {

        storage.clear();

        storage.save(new Resume("uuid1", "Alexeev Anton"));

        storage.save(ResumeTestData.createResume("uuid3", "Kraeva Svetlana"));

        Resume resume = storage.get("uuid3");

        ResumeTestData.printResume(resume);
    }
}
