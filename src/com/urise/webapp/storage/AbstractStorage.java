package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doClear();

    protected abstract int doSize();

    protected abstract int findResumeIndex(String uuid);

    protected abstract void doUpdate(Resume r, int index);

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(int index);

    protected abstract Resume doGet(int index);

    protected abstract Resume[] doGetAll();

    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistResumeException(r.getUuid());
        }
        doUpdate(r, index);
    }

    public Resume[] getAll() {
        return doGetAll();
    }

    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistResumeException(r.getUuid());
        } else {
            doSave(r, index);
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException(uuid);
        }
        doDelete(index);
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            throw new NotExistResumeException(uuid);
        }
        return doGet(index);
    }

    public int size() {
        return doSize();
    }

    public void clear() {
        doClear();
    }


}