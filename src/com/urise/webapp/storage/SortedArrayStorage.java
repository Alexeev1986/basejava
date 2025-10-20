package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.FullStorageArrayException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + r.getUuid() + ") не существует, обновление не возможно.");
        }
        storage[index] = r;
        System.out.println("Резюме (" + storage[index] + ") успешно обновлено.");
    }

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new FullStorageArrayException("Ошибка: база данных заполнена, сохранение не возможно.");
        }
        int index = findResumeIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistResumeException("Ошибка: резюме (" + r.getUuid() + ") уже существует, сохранение не возможно.");
        }
        int insetIndex = -index - 1;
        System.arraycopy(storage, insetIndex, storage, insetIndex + 1, size - insetIndex);
        storage[insetIndex] = r;
        size++;
    }

    @Override
    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException("Ошибка: резюме (" + uuid + ") не существует, удаление не возможно.");
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0, size, searchKey);
    }
}
