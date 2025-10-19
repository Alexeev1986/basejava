package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.FullStorageArrayException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            throw new FullStorageArrayException("Ошибка: база данных заполнена, сохранение не возможно.");
        }
        if (findResumeIndex(r.getUuid()) >= 0) {
            throw new ExistResumeException("Ошибка: резюме (" + r.getUuid() +
                    ") уже существует, сохранение не возможно.");
        }
        storage[size++] = r;
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid + ") не существует");
        }
        return storage[index];
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + r.getUuid() +
                    ") не существует, обновление не возможно.");
        }
        storage[index] = r;
        System.out.println("Резюме (" + storage[index] + ") успешно обновлено.");
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid +
                    ") не существует, удаление не возможно.");
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    private int findResumeIndex(String uuid) {
        int isPresent = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                isPresent = i;
                break;
            }
        }
        return isPresent;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

