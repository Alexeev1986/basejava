package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doUpdate(Resume r, int index) {
        storage[index] = r;
    }

    @Override
    protected void doSave(Resume r, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, index);
        size++;
    }

    @Override
    protected Resume doGet(String uuid, int index) {
        return storage[index];
    }

    protected abstract int findResumeIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void doDelete(String uuid, int index);
}

