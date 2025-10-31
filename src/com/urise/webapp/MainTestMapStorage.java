package com.urise.webapp;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.MapStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestMapStorage {
    private static final MapStorage MAP_STORAGE = new MapStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "Курочкин Евгений Николаевич");
        final Resume r2 = new Resume("uuid2", "Полохов Алексей Владимирович");
        final Resume r3 = new Resume("uuid3", "Полохов Алексей Владимирович");
        try {
            MAP_STORAGE.save(r3);
            MAP_STORAGE.save(r1);
            MAP_STORAGE.save(r2);

            MAP_STORAGE.save(r2);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Size: " + MAP_STORAGE.size());
        try {
            System.out.println("Get r1: " + MAP_STORAGE.get(r1.getUuid()));
            System.out.println("Get dummy: " + MAP_STORAGE.get("dummy"));
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        final Resume r4 = new Resume("uuid4");
        try {
            MAP_STORAGE.update(r2);
            MAP_STORAGE.update(r4);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        try {
            MAP_STORAGE.delete(r1.getUuid());
            MAP_STORAGE.delete(r4.getUuid());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        MAP_STORAGE.clear();
        printAll();
        System.out.println("Size: " + MAP_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : MAP_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
