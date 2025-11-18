package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.ObjectStreamPathStorage;
import com.urise.webapp.storage.strategy.ObjectStreamSerializer;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}