package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }
    @Test
    void getAllSorted() {
        Resume[] getAll = storage.getAllSorted().toArray(new Resume[0]);
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Arrays.sort(expected, (o1, o2) -> {
            int result = o1.getFullName().compareTo(o2.getFullName());
            if (result == 0) {
                return o1.getUuid().compareTo(o2.getUuid());
            }
            return result;
        });
        assertEquals(3, getAll.length);
        assertArrayEquals(getAll, expected);
    }

    @Test
    void saveOverflow() {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("Unknow" + i));
            } catch (StorageException e) {
                fail("Ошибка: переполнение произошло раньше времени");
            }
        }
        assertEquals(AbstractArrayStorage.STORAGE_LIMIT, storage.size());
        assertThrows(StorageException.class, () -> storage.save(new Resume("OverflowName")));
    }
}