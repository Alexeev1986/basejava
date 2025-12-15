package com.urise.webapp.storage.junit_five;

import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamSerializer;


public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}