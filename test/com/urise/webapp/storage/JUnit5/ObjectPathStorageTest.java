package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}