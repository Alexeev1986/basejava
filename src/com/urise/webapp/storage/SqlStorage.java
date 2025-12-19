package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistResumeException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                   UPDATE resume
                                                                      SET full_name = ?
                                                                    WHERE uuid = ?
                                                                    """)) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistResumeException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                   DELETE FROM contact
                                                                   WHERE resume_uuid = ?
                                                                   """)) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            insertContact(r, conn);
            return null;
        }
        );
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("""
                                                            INSERT INTO resume (uuid, full_name)
                                                            VALUES (?, ?)
                                                            """)) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContact(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("""
                                      SELECT *
                                        FROM resume r
                                   LEFT JOIN contact c
                                          ON r.uuid = c.resume_uuid
                                       WHERE r.uuid = ?
                                   """, ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistResumeException(uuid);
                }
                Resume r = new Resume(uuid, rs.getString("full_name"));
                do {
                    String value = rs.getString("value");
                    if (value != null) {
                        r.setContact(ContactType.valueOf(rs.getString("type")), value);
                    }
                } while (rs.next());
                return r;
            });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistResumeException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                    SELECT uuid, full_name
                                                                      FROM resume
                                                                  ORDER BY full_name, uuid
                                                                 """)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String uuid = rs.getString("uuid").trim();
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name").trim()));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid").trim());
                    String value = rs.getString("value");
                    if (value != null) {
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        r.setContact(type, value);
                    }
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(uuid) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return (rs.next()) ? rs.getInt(1) : 0;
        });
    }

    private void insertContact(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO contact (resume_uuid, type, value)" +
                        " VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
