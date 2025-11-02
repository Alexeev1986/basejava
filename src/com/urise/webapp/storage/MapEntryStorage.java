package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapEntryStorage extends AbstractStorage {
    protected final Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Object findResumeSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        Map.Entry<String, Resume> entry = (Map.Entry<String, Resume>) searchKey;
        entry.setValue(r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doDelete(String uuid, Object searchKey) {
        Map.Entry<String, Resume> entry = (Map.Entry<String, Resume>) searchKey;
        map.remove(entry.getKey());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Resume doGet(String uuid, Object searchKey) {
        Map.Entry<String, Resume> entry = (Map.Entry<String, Resume>) searchKey;
        return entry.getValue();
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
