package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.storage.strategy.JsonStreamSerializer;

import java.io.File;

public class MainTestFile {
    private static final File directory =  new File(".\\storage");
    private static final Storage storage = new FileStorage(directory, new JsonStreamSerializer());

    public static void main(String[] args) {

        storage.clear();

        storage.save(new Resume("Alexeev Anton", "uuid1"));

        storage.save(ResumeTestData.createResume("Kraeva Svetlana", "uuid3"));


    }
}
