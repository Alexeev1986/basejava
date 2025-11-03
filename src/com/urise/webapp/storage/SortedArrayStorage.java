package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer findResumeSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Unknow");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected void doDelete(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size] = null;
        size--;
    }

    @Override
    protected void insertElement(Resume r, Integer index) {
        int insertIndex = -(Integer) index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }
}
