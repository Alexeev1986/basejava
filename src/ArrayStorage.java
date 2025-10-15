import java.util.Arrays;

public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int resumeCount;

    public void clear() {
        Arrays.fill(storage, 0, resumeCount, null);
        resumeCount = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            storage[resumeCount++] = r;
        }
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            return null;
        }
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < resumeCount; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, resumeCount - i - 1);
                storage[--resumeCount] = null;
                return ;
            }
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    public int size() {
        return resumeCount;
    }
}
