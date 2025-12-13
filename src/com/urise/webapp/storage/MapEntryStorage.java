package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapEntryStorage extends AbstractStorage<Map.Entry<String, Resume>> {
    protected final Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExist(Map.Entry<String, Resume> searchKey) {
        return searchKey != null;
    }

    @Override
    protected Map.Entry<String, Resume> findResumeSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Map.Entry<String, Resume> searchKey) {
        searchKey.setValue(r);
    }

    @Override
    protected void doSave(Resume r, Map.Entry<String, Resume> searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Map.Entry<String, Resume> searchKey) {
        map.remove(searchKey.getKey());
    }

    @Override
    protected Resume doGet(Map.Entry<String, Resume> searchKey) {
        return searchKey.getValue();
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
