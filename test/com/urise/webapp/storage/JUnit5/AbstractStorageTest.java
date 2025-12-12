package urise.webapp.storage.JUnit5;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String UUID_4 = "uuid4";
    protected static final String UUID_5 = "uuid5";
    protected static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, "Курочкин Евгений Николаевич");
    protected static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, "Иванов Никита Николаевич");
    protected static final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, "Полохов Алексей Владимирович");
    protected static final Resume RESUME_4 = ResumeTestData.createResume(UUID_4, "Петров Данила Васильевич");
    protected static final Resume RESUME_5 = ResumeTestData.createResume(UUID_5, "Самар Павел Вечиславович");
    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1, "Самар Павел Вечиславович");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistResumeException.class, () -> storage.update(RESUME_5));
    }

    @Test
    void getAllSorted() {
        List<Resume> getAll = storage.getAllSorted();
        assertEquals(3, getAll.size());
        List<Resume> expected = List.of(RESUME_2, RESUME_1, RESUME_3);
        assertEquals(expected, getAll);
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test
    void saveExistResume() {
        assertThrows(ExistResumeException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertThrows(NotExistResumeException.class, () -> storage.get(UUID_1));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistResumeException.class, () -> storage.delete(UUID_5));
    }

    @Test
    void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
        assertEquals(RESUME_3, storage.get(UUID_3));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistResumeException.class, () -> storage.get(UUID_5));
    }
}