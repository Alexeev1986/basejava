package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.List;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public List<Resume> getAllSorted() {
        Resume[] copy = Arrays.copyOf(storage, size);
        Arrays.sort(copy, (o1, o2) -> {
            int result = o1.getFullName().compareTo(o2.getFullName());
            if (result == 0) {
                return o1.getUuid().compareTo(o2.getUuid());
            }
            return result;
        });
        return Arrays.asList(copy);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void doSave(Resume r, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        insertElement(r, index);
        size++;
    }

    @Override
    protected Resume doGet(String uuid, Object index) {
        return storage[(Integer) index];
    }

    protected abstract Integer findResumeSearchKey(String uuid);

    protected abstract void insertElement(Resume r, Object index);

    protected abstract void doDelete(String uuid, Object index);
}

