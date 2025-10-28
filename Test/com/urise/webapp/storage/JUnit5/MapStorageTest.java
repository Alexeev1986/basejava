package com.urise.webapp.storage.JUnit5;

import com.urise.webapp.storage.MapStorage;

class MapStorageTest extends AbstractStorageTest{
    protected MapStorageTest() {
        super(new MapStorage());
    }
}