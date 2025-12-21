package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.sql.SqlHelper;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestBase {
    private static final SqlHelper sqlHelper = new SqlHelper(() ->
            DriverManager.getConnection(Config.get().getDbUrl(), Config.get().getDbUser(),
                    Config.get().getDbPassword()));

    public static void main(String[] args) {
        Map<String, String> resume = sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume")) {
                Map<String, String> result = new LinkedHashMap<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    result.put(uuid, fullName);
                }
                return result;
            }
        });
        for (Map.Entry<String, String> e : resume.entrySet()) {
            System.out.println(e.getValue() + "    " + e.getKey());
        }
    }
}
