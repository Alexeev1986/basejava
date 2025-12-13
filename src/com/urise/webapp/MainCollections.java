package com.urise.webapp;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainCollections {
    private static final String UUID_2 = "uuid2";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_1 = "uuid1";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Курочкин Евгений Николаевич");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Иванов Никита Николаевич");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Полохов Алексей Владимирович");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Петров Данила Васильевич");
    private static final Collection<Resume> COLLECTION = new ArrayList<>();

    public static void main(String[] args) {
        COLLECTION.add(RESUME_1);
        COLLECTION.add(RESUME_2);
        COLLECTION.add(RESUME_3);
        printList();

        System.out.println("Delete UUID_1");
        COLLECTION.removeIf(r -> Objects.equals(r.getUuid(), UUID_1));
        printList();

        Map<String, Resume> map = new HashMap<>();
        {
            map.put(UUID_1, RESUME_1);
            map.put(UUID_2, RESUME_2);
            map.put(UUID_3, RESUME_3);
            map.put(UUID_4, RESUME_4);
        }
        System.out.println("Map :");
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }
        System.out.println("Map.Entry");
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        printAnyList(resumes);
    }

    private static void printList() {
        for (Resume r : COLLECTION) {
            System.out.println(r);
        }
    }

    private static void printAnyList(List<Resume> list) {
        for (Resume r : list) {
            System.out.println(r);
        }
    }
}
