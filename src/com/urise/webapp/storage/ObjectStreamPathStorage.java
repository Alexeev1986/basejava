package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.SerializerStrategy;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dirPath, SerializerStrategy serializerStrategy) {
        super(dirPath, serializerStrategy);
    }
}
