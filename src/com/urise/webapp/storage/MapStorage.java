package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    protected int findResumeSearchKey(String uuid) {
        return map.containsKey(uuid) ? 0 : -1;
    }

    @Override
    protected void doUpdate(Resume r, int searchKey) {
        map.put(r.getUuid(), r);
    }


    @Override
    protected void doSave(Resume r, int searchKey) {
        if (map.size() >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, int searchKey) {
        map.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid, int searchKey) {
        return map.get(uuid);
    }
}

