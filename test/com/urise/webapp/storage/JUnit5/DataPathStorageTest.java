package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}