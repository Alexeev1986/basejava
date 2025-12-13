package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        executing("DELETE FROM resume ", PreparedStatement::execute);
    }

    @Override
    public void update(Resume r) {
        executing("UPDATE resume SET full_name=? WHERE uuid=?", exe -> {
            exe.setString(1, r.getFullName());
            exe.setString(2, r.getUuid());
            exe.execute();
            if (exe.executeUpdate() == 0) {
                throw new NotExistResumeException(r.getUuid());
            }
        });
    }

    @Override
    public void save(Resume r) {
        executing("INSERT INTO resume (uuid, full_name) VALUES (?,?)", exe -> {
            exe.setString(1, r.getUuid());
            exe.setString(2, r.getFullName());
            exe.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        return executeAndReturn("SELECT uuid, full_name FROM resume r WHERE r.uuid =?", exe -> {
            exe.setString(1, uuid);
            ResultSet rs = exe.executeQuery();
            if (!rs.next()) {
                throw new NotExistResumeException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        executing("DELETE FROM resume r WHERE r.uuid =?", exe -> {
            exe.setString(1, uuid);
            if (exe.executeUpdate() == 0) {
                throw new NotExistResumeException(uuid);
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return executeAndReturn("SELECT uuid, full_name FROM resume ORDER BY full_name ASC, uuid", exe -> {
            ResultSet rs = exe.executeQuery();
            List<Resume> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name").trim()));
            }
            return result;
        });
    }

    @Override
    public int size() {
        return executeAndReturn("SELECT COUNT(uuid) FROM resume", exe -> {
            ResultSet rs = exe.executeQuery();
            return (rs.next()) ? rs.getInt(1) : 0;
        });
    }

    interface SqlExe {
        void execute(PreparedStatement ps) throws SQLException;
    }

    private void executing(String sql, SqlExe exe) {
        try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
            exe.execute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistResumeException(null);
            }
            throw new StorageException(e);
        }
    }

    interface SqlExeAndReturn<T> {
        T executeAndReturned(PreparedStatement ps) throws SQLException;
    }

    private <T> T executeAndReturn(String sql, SqlExeAndReturn<T> exe) {
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            return exe.executeAndReturned(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
