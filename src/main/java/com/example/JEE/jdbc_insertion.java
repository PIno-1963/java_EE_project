package com.example.java_ee_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbc_insertion {
    public static void insertData(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO ident (name, email) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);

            statement.executeUpdate();
        }
    }
}