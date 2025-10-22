package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistResumeException(r.getUuid());
        }
        storage[index] = r;
        System.out.println("Резюме (" + storage[index] + ") успешно обновлено.");
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        int index = findResumeIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistResumeException(r.getUuid());
        }
        insertElement(r, index);
        size++;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException(uuid);
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size] = null;
        size--;
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException(uuid);
        }
        return storage[index];
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);
}

