package com.urise.webapp.storage;

import com.urise.webapp.exception.AnExistingResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(String uuid) {
        if (indexPresentResume(uuid) < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid +
                    ") не существует, обновление не возможно.");
        }
        System.out.println("Введите обновляемый параметр");
        Scanner console = new Scanner(System.in);
        String newResume = console.nextLine().trim();
        if (newResume.isBlank()) {
            throw new IllegalArgumentException("Ошибка: обновляемы параметр не может быть пустым.");
        }
        int index = indexPresentResume(newResume);
        if (index >= 0 && size != 0) {
            throw new AnExistingResumeException("Ошибка: резюме с таким именем уже(" + newResume +
                    ") уже существует, обновление  не возможно.");
        }
        storage[index].uuid = newResume;
        System.out.println("Резюме (" + uuid + ") -> (" + newResume + ") успешно обновлено.");
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            throw new OutOfMemoryError("Ошибка: база данных заполнена, сохранение не возможно.");
        }
        if (indexPresentResume(r.uuid) >= 0 && size != 0) {
            throw new AnExistingResumeException("Ошибка: резюме (" + r.uuid +
                    ") уже существует, сохранение не возможно.");
        }
        storage[size++] = r;
    }

    public Resume get(String uuid) {
        int index = indexPresentResume(uuid);
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid + ") не существует");
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = indexPresentResume(uuid);
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid +
                    ") не существует, удаление не возможно.");
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int indexPresentResume(String uuid) {
        int isPresent = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                isPresent = i;
                break;
            }
        }
        return isPresent;
    }
}

