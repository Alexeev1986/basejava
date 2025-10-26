package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> list = new ArrayList<>();

    @Override
    public int doSize() {
        return list.size();
    }

    @Override
    public void doClear() {
        list.clear();
    }

    @Override
    protected int findResumeIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Resume r, int index) {
        list.set(index, r);
    }

    @Override
    protected void doSave(Resume r, int index) {
        list.add(r);
    }

    @Override
    protected void doDelete(int index) {
        list.remove(index);
    }

    @Override
    protected Resume doGet(int index) {
        return list.get(index);
    }

    @Override
    protected Resume[] doGetAll() {
        return list.toArray(new Resume[0]);
    }
}


