package com.urise.webapp.web;

import com.urise.webapp.sql.SqlHelper;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResumeService {
    private final SqlHelper sqlHelper = new SqlHelper(() ->
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/resumes", "postgres",
                    "390227"));

    public Map<String, String> getResumes() {
        Map<String, String> resumes = new LinkedHashMap<>();
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT uuid, full_name FROM resume")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    resumes.put(rs.getString("uuid"), rs.getString("full_name"));
                }
            }
            return resumes;
        });
    }
}
