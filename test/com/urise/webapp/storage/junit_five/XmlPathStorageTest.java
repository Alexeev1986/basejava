package com.urise.webapp.storage.junit_five;

import com.urise.webapp.storage.PathStorage;
import com.urise.webapp.storage.strategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}