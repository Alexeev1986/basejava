package com.urise.webapp.exception;

public class NotExistResumeException extends StorageException {
    public NotExistResumeException(String uuid) {
        super("Resume " + uuid + " not already exist", uuid);
    }
}
