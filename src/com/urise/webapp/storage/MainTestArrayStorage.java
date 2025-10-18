package com.urise.webapp.storage;

import com.urise.webapp.exception.AnExistingResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.uuid = "uuid1";
        Resume r2 = new Resume();
        r2.uuid = "uuid2";
        Resume r3 = new Resume();
        r3.uuid = "uuid3";
        try {
            ARRAY_STORAGE.save(r1);
            ARRAY_STORAGE.save(r2);
            ARRAY_STORAGE.save(r3);
            ARRAY_STORAGE.save(r2);
        } catch (AnExistingResumeException | OutOfMemoryError e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
        try {
            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.uuid));
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistResumeException e) {
            System.out.println(e.getMessage());
        }
        Resume r4 = new Resume();
        r4.uuid = "uuid4";
        try {
            ARRAY_STORAGE.update(r2.uuid);
            ARRAY_STORAGE.update(r4.uuid);
        } catch (NotExistResumeException | AnExistingResumeException| IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        try {
            ARRAY_STORAGE.delete(r1.uuid);
            ARRAY_STORAGE.delete(r4.uuid);
        } catch (NotExistResumeException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
