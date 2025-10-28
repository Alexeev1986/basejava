package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public static final int STORAGE_LIMIT = 10_000;

    public void update(Resume r) {
        int searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        int searchKey = findResumeSearchKey(r.getUuid());
        if (searchKey >= 0) {
            throw new ExistResumeException(r.getUuid());
        } else {
            doSave(r, searchKey);
        }
    }

    public void delete(String uuid) {
        int searchKey = getExistingSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    public Resume get(String uuid) {
        int searchKey = getExistingSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    private int getExistingSearchKey(String uuid) {
        int searchKey = findResumeSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistResumeException(uuid);
        }
        return searchKey;
    }

    protected abstract int findResumeSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, int searchKey);

    protected abstract void doSave(Resume r, int searchKey);

    protected abstract void doDelete(String uuid, int searchKey);

    protected abstract Resume doGet(String uuid, int searchKey);
}