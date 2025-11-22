package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.ObjectStreamFileStorage;
import com.urise.webapp.storage.strategy.ObjectStreamStreamSerializer;


public class ObjectStreamFileStorageTest extends AbstractStorageTest {

    public ObjectStreamFileStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR, new ObjectStreamStreamSerializer()));
    }
}