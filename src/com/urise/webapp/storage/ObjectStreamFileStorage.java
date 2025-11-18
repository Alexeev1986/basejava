package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.SerializerStrategy;

import java.io.File;

public class ObjectStreamFileStorage extends AbstractFileStorage {

    public ObjectStreamFileStorage(File directory, SerializerStrategy serializerStrategy) {
        super(directory, serializerStrategy);
    }
}
