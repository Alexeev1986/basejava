package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    protected final Map<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public List<Resume> doGetAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey != -1;
    }

    @Override
    protected Object findResumeSearchKey(String uuid) {
        return map.containsKey(uuid) ? 0 : -1;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }


    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid, Object searchKey) {
        map.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        return map.get(uuid);
    }
}

