package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.StorageException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> exec) {
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            return exec.execute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistResumeException(null);
            }
            throw new StorageException(e);
        }
    }
}
