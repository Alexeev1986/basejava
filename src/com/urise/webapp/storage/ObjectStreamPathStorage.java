package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.StreamSerializer;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dirPath, StreamSerializer streamSerializer) {
        super(dirPath, streamSerializer);
    }
}
