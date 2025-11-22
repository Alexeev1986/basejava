package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamStreamSerializer;


public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStreamSerializer()));
    }
}