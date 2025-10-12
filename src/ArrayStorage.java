import java.util.Arrays;

public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r != null) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                storage[size] = null;
                size--;
                return;
            }
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    public int size() {
        return size;
    }
}
