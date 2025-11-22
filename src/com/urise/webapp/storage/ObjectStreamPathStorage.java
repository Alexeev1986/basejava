package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.StreamSerializer;

public class ObjectStreamPathStorage extends PathStorage {

    public ObjectStreamPathStorage(String dirPath, StreamSerializer streamSerializer) {
        super(dirPath, streamSerializer);
    }
}
