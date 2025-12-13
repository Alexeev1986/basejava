package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.StreamSerializer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerializer streamSerializer;

    public PathStorage(String directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");

        this.directory = Paths.get(directory);
        this.streamSerializer = streamSerializer;
        if (!Files.isDirectory(this.directory) || !Files.isWritable(this.directory)) {
            throw new IllegalArgumentException(directory + " is not directory or is not writable");
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected Path findResumeSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try (OutputStream os = Files.newOutputStream(path)) {
            streamSerializer.doWrite(r, new BufferedOutputStream(os));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try (OutputStream os = Files.newOutputStream(path)) {
            streamSerializer.doWrite(r, new BufferedOutputStream(os));
        } catch (IOException e) {
            throw new StorageException("Failed to create Path for resume storage" +
                    path, getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            return streamSerializer.doRead(new BufferedInputStream(is));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}

