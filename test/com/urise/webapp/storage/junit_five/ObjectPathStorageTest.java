package com.urise.webapp.storage.junit_five;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}