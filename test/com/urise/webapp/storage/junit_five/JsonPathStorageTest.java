package com.urise.webapp.storage.junit_five;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}