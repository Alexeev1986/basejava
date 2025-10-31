package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public static final int STORAGE_LIMIT = 10_000;

    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);

    }

    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        doDelete(uuid, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return doGet(uuid, searchKey);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        list.sort(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int result = o1.getFullName().compareTo(o2.getFullName());
                if (result == 0) {
                    return o1.getUuid().compareTo(o2.getUuid());
                }
                return result;
            }
        });

        return list;
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = findResumeSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistResumeException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = findResumeSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistResumeException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Integer findResumeSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(String uuid, Object searchKey);

    protected abstract Resume doGet(String uuid, Object searchKey);

    protected  abstract List<Resume> doGetAll();
}