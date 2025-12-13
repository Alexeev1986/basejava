package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<K> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume r) {
        LOG.info("Update " + r);
        K searchKey = getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        LOG.info("Save " + r);
        K searchKey = getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        K searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        K searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = doGetAll();
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    private K getExistingSearchKey(String uuid) {
        K searchKey = findResumeSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistResumeException(uuid);
        }
        return searchKey;
    }

    private K getNotExistingSearchKey(String uuid) {
        K searchKey = findResumeSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistResumeException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(K searchKey);

    protected abstract K findResumeSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, K searchKey);

    protected abstract void doSave(Resume r, K searchKey);

    protected abstract void doDelete(K searchKey);

    protected abstract Resume doGet(K searchKey);

    protected abstract List<Resume> doGetAll();
}