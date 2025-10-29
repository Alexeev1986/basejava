package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    protected int findResumeSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume r, int searchKey) {
        list.set(searchKey, r);
    }

    @Override
    protected void doSave(Resume r, int searchKey) {
        list.add(r);
    }

    @Override
    protected void doDelete(String uuid, int searchKey) {
        list.remove(searchKey);
    }

    @Override
    protected Resume doGet(String uuid, int searchKey) {
        return list.get(searchKey);
    }
}


