package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.ObjectStreamFileStorage;


public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR));
    }
}