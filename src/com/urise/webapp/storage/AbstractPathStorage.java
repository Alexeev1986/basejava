package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.SerializerStrategy;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializerStrategy serializerStrategy;

    protected AbstractPathStorage(String dir, SerializerStrategy serializerStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.serializerStrategy = serializerStrategy;
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
            serializerStrategy.doWrite(r, new BufferedOutputStream(os));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try (OutputStream os = Files.newOutputStream(path)) {
            serializerStrategy.doWrite(r, new BufferedOutputStream(os));
        } catch (IOException e) {
            throw new StorageException("Failed to create Path for resume storage" +
                    path, path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            return serializerStrategy.doRead(new BufferedInputStream(is));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumes = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                resumes.add(doGet(path));
            }
        } catch (IOException e) {
            throw new StorageException("Dir read error", e);
        }
        return resumes;
    }

    @Override
    public void clear() {
        try (DirectoryStream<Path> files = Files.newDirectoryStream(directory)){
            for (Path file : files) {
                doDelete(file);
            }
        } catch (IOException e) {
            throw new StorageException("Path delete error", e);
        }
    }

    @Override
    public int size() {
        try (Stream<Path> files = Files.list(directory)) {
            return (int) files.count();
        } catch (IOException e) {
            throw new StorageException("Dir read error", e);
        }
    }
}

