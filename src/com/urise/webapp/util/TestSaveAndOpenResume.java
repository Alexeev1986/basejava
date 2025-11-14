package com.urise.webapp.util;

import com.urise.webapp.ResumeTestData;
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
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(r);
            oos.close();
        } catch (IOException e) {
            throw new StorageException("IO error", fileName.getName(), e);
        }
    }

    private static Resume openResume(File fileName) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            Resume resume = (Resume) ois.readObject();
            return resume;

        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("IO error or ClassNotFoundException error", fileName.getName(), null);
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new StorageException("IO error", null);
            }
        }
    }
}
