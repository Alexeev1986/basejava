package com.urise.webapp.storage.junit_five;

import com.urise.webapp.Config;

public class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(Config.get().getSqlStorage());
    }
}
