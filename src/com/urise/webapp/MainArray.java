package com.urise.webapp;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.Storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainArray {
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save fullName | " +
                    "update uuid fullName | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String param = null;
            if (params.length == 3) {
                param = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(param);
                    try {
                        ARRAY_STORAGE.save(r);
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    printAll();
                    break;
                case "update":
                    r = new Resume(param, params[2]);
                    try {
                        ARRAY_STORAGE.update(r);
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    printAll();
                    break;
                case "delete":
                    try {
                        ARRAY_STORAGE.delete(param);
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    printAll();
                    break;
                case "get":
                    try {
                        System.out.println(ARRAY_STORAGE.get(param));
                    } catch (StorageException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAllSorted().toArray(new Resume[0]);
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
