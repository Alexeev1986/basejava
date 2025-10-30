package com.urise.webapp;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestListStorage {
    private static final ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "Курочкин Евгений Николаевич");
        final Resume r2 = new Resume("uuid2", "Полохов Алексей Владимирович");
        final Resume r3 = new Resume("uuid3", "Полохов Алексей Владимирович");

        try {
            LIST_STORAGE.save(r1);

            LIST_STORAGE.save(r3);
            LIST_STORAGE.save(r2);
            LIST_STORAGE.save(r2);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Size: " + LIST_STORAGE.size());
        try {
            System.out.println("Get r1: " + LIST_STORAGE.get(r1.getUuid()));
            System.out.println("Get dummy: " + LIST_STORAGE.get("dummy"));
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        final Resume r4 = new Resume("uuid4");
        try {
            LIST_STORAGE.update(r2);
            LIST_STORAGE.update(r4);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        try {
            LIST_STORAGE.delete(r1.getUuid());
            LIST_STORAGE.delete(r4.getUuid());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        LIST_STORAGE.clear();
        printAll();
        System.out.println("Size: " + LIST_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : LIST_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
