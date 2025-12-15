package urise.webapp.storage.junit;

import com.urise.webapp.storage.MapUuidStorage;

class MapUuidStorageTest extends AbstractStorageTest {
    protected MapUuidStorageTest() {
        super(new MapUuidStorage());
    }
}