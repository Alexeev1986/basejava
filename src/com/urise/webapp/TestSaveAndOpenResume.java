package com.urise.webapp;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class TestSaveAndOpenResume {
    public static void main(String[] args) {
        Resume r1 = ResumeTestData.createResume("uuid9", "Григорий Кислицин");
        File path = new File("resume.bin");
        saveResume(r1, path);
        Resume readFile = openResume(path);
        ResumeTestData.printResume(readFile);
    }

    private static void saveResume(Resume r, File fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(r);
        } catch (IOException e) {
            throw new StorageException("IO error", fileName.getName(), e);
        }
    }

    private static Resume openResume(File fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (Resume) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("IO error or ClassNotFoundException error", fileName.getName(), null);
        }
    }
}
