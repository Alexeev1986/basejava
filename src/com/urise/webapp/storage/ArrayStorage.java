package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object findResumeSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume r, Object index) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(Object index) {
        storage[(Integer) index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}

