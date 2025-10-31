package com.urise.webapp;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "Курочкин Евгений Николаевич");
        final Resume r2 = new Resume("uuid2", "Иванов Никита Николаевич");
        final Resume r3 = new Resume("uuid3", "Полохов Алексей Владимирович");
        try {
            ARRAY_STORAGE.save(r1);
            ARRAY_STORAGE.save(r2);
            ARRAY_STORAGE.save(r3);
            ARRAY_STORAGE.save(r2);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
        try {
            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        final Resume r4 = new Resume("uuid4", "Самар Павел Вечиславович");
        try {
            ARRAY_STORAGE.update(r2);
            ARRAY_STORAGE.update(r4);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        try {
            ARRAY_STORAGE.delete(r1.getUuid());
            ARRAY_STORAGE.delete(r4.getUuid());
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted().toArray(new Resume[0])) {
            System.out.println(r);
        }
    }
}
