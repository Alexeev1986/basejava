package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.ObjectStreamStorage;


public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}