package com.urise.webapp.storage.junit_five;

import com.urise.webapp.Config;
import com.urise.webapp.storage.SqlStorage;

public class SqlStorageTest extends AbstractStorageTest {

    protected SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUser(), Config.get().getDbPassword()));
    }
}
