package urise.webapp.storage.JUnit5;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.AbstractArrayStorage;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
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