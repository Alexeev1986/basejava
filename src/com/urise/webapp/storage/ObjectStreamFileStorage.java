package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.StreamSerializer;

import java.io.File;

public class ObjectStreamFileStorage extends AbstractFileStorage {

    public ObjectStreamFileStorage(File directory, StreamSerializer streamSerializer) {
        super(directory, streamSerializer);
    }
}
